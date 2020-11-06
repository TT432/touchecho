package com.kilkags.touchecho.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.World;

/**
 * @author DustW
 */
public class EntitySlimeKing extends EntityMob {
    public static final String ENTITY_SLIME_KING = "slime_king";

    public EntitySlimeKing(World worldIn) {
        super(worldIn);
        this.setSize(3F, 3F);
        this.moveHelper = new SlimeKingMoveHelper(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1);
    }

    protected int getJumpDelay() {
        return this.rand.nextInt(20) + 10;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F * 48;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntitySlimeKingAi.SlimeFloatAi(this));
        this.tasks.addTask(2, new EntitySlimeKingAi.SlimeAttackAi(this));
        this.tasks.addTask(3, new EntitySlimeKingAi.SlimeFaceRandomAi(this));
        this.tasks.addTask(5, new EntitySlimeKingAi.SlimeHopAi(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }


    static class SlimeKingMoveHelper extends EntityMoveHelper {
        private float yRot;
        private int jumpDelay;
        private final EntitySlimeKing slime;
        private boolean isAggressive;

        public SlimeKingMoveHelper(EntitySlimeKing slimeIn) {
            super(slimeIn);
            this.slime = slimeIn;
            this.yRot = 180.0F * slimeIn.rotationYaw / (float) Math.PI;
        }

        public void setDirection(float yRot, boolean isAggressive) {
            this.yRot = yRot;
            this.isAggressive = isAggressive;
        }

        public void setSpeed(double speedIn) {
            this.speed = speedIn;
            this.action = EntityMoveHelper.Action.MOVE_TO;
        }

        @Override
        public void onUpdateMoveHelper() {
            this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
            this.entity.rotationYawHead = this.entity.rotationYaw;
            this.entity.renderYawOffset = this.entity.rotationYaw;

            if (this.action != EntityMoveHelper.Action.MOVE_TO) {
                this.entity.setMoveForward(0.0F);
            } else {
                this.action = EntityMoveHelper.Action.WAIT;

                if (this.entity.onGround) {
                    this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();

                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.slime.getJumpHelper().setJumping();
                        this.slime.playSound(SoundEvents.ENTITY_SLIME_JUMP, this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                    } else {
                        this.slime.moveStrafing = 0.0F;
                        this.slime.moveForward = 0.0F;
                        this.entity.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.entity.setAIMoveSpeed((float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                }
            }
        }
    }
}
