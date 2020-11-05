package com.kilkags.touchecho.entity;

import com.kilkags.touchecho.lotus.LotusSymphony;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntitySlimeKing extends EntityMob {
    public static final String ENTITY_SLIME_KING = "slime_king";
    public final SlimeMoveHelper MOVE_HELPER = new SlimeMoveHelper(this);

    public EntitySlimeKing(World worldIn) {
        super(worldIn);
        this.setSize(3F, 3F);
    }

    @Override
    public @NotNull EntityMoveHelper getMoveHelper() {
        return this.MOVE_HELPER;
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
        this.tasks.addTask(1, new SlimeFloatAi(this));
        this.tasks.addTask(2, new SlimeAttackAi(this));
        this.tasks.addTask(3, new SlimeFaceRandomAi(this));
        this.tasks.addTask(5, new SlimeHopAi(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    static class SlimeAttackAi extends EntityAIBase {
        private final EntitySlimeKing slime;
        private int growTieredTimer;
        private EntityLivingBase slimeTarget;

        public SlimeAttackAi(EntitySlimeKing slimeIn) {
            this.slime = slimeIn;
            this.setMutexBits(2);
        }

        @Override
        public boolean shouldExecute() {
            slimeTarget = slime.getAttackTarget();
            if (!LotusSymphony.entityExists(slimeTarget)) {
                return false;
            }
            else {
                return !(slimeTarget instanceof EntityPlayer) || !((EntityPlayer) slimeTarget).capabilities.disableDamage;
            }
        }

        @Override
        public void startExecuting() {
            this.growTieredTimer = 300;
            super.startExecuting();
        }

        @Override
        public boolean shouldContinueExecuting() {
            slimeTarget = slime.getAttackTarget();
            if (!LotusSymphony.entityExists(slimeTarget)) {
                return false;
            }
            else if (slimeTarget instanceof EntityPlayer && ((EntityPlayer) slimeTarget).capabilities.disableDamage) {
                return false;
            }
            else {
                return --this.growTieredTimer > 0;
            }
        }

        @Override
        public void updateTask() {
            slimeTarget = slime.getAttackTarget();
            if(LotusSymphony.entityExists(slimeTarget)) {
                this.slime.faceEntity(slimeTarget, 10.0F, 10.0F);
            }
            slime.MOVE_HELPER.setDirection(this.slime.rotationYaw, true);
        }
    }

    static class SlimeFaceRandomAi extends EntityAIBase {
        private final EntitySlimeKing slime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public SlimeFaceRandomAi(EntitySlimeKing slimeIn) {
            this.slime = slimeIn;
            this.setMutexBits(2);
        }

        @Override
        public boolean shouldExecute() {
            return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.isPotionActive(MobEffects.LEVITATION));
        }

        @Override
        public void updateTask() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
                this.chosenDegrees = (float)this.slime.getRNG().nextInt(360);
            }

            slime.MOVE_HELPER.setDirection(this.chosenDegrees, false);
        }
    }

    static class SlimeFloatAi extends EntityAIBase {
        private final EntitySlimeKing slime;

        public SlimeFloatAi(EntitySlimeKing slimeIn) {
            this.slime = slimeIn;
            this.setMutexBits(5);
            ((PathNavigateGround)slimeIn.getNavigator()).setCanSwim(true);
        }

        @Override
        public boolean shouldExecute() {
            return this.slime.isInWater() || this.slime.isInLava();
        }

        @Override
        public void updateTask() {
            if (this.slime.getRNG().nextFloat() < 0.8F) {
                this.slime.getJumpHelper().setJumping();
            }

            slime.MOVE_HELPER.setSpeed(1.2D);
        }
    }

    static class SlimeHopAi extends EntityAIBase {
        private final EntitySlimeKing slime;

        public SlimeHopAi(EntitySlimeKing slimeIn) {
            this.slime = slimeIn;
            this.setMutexBits(5);
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void updateTask() {
            slime.MOVE_HELPER.setSpeed(1.0D);
        }
    }

    static class SlimeMoveHelper extends EntityMoveHelper {
        private float yRot;
        private int jumpDelay;
        private final EntitySlimeKing slime;
        private boolean isAggressive;

        public SlimeMoveHelper(EntitySlimeKing slimeIn) {
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
