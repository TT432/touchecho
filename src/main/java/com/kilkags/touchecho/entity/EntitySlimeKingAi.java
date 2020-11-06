package com.kilkags.touchecho.entity;

import com.kilkags.touchecho.entity.EntitySlimeKing.SlimeKingMoveHelper;
import com.kilkags.touchecho.lotus.LotusSymphony;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.PathNavigateGround;

/**
 * @author DustW
 */
public class EntitySlimeKingAi {
    static class SlimeAttackAi extends EntityAIBase {
        private final EntitySlimeKing slime;
        private int growTieredTimer;

        public SlimeAttackAi(EntitySlimeKing slimeIn) {
            this.slime = slimeIn;
            this.setMutexBits(2);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase slimeTarget = slime.getAttackTarget();
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
            EntityLivingBase slimeTarget = slime.getAttackTarget();
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
            EntityLivingBase slimeTarget = slime.getAttackTarget();
            if(LotusSymphony.entityExists(slimeTarget)) {
                this.slime.faceEntity(slimeTarget, 10.0F, 10.0F);
            }
            ((SlimeKingMoveHelper)slime.getMoveHelper()).setDirection(this.slime.rotationYaw, true);
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

            ((SlimeKingMoveHelper)slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
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

            ((SlimeKingMoveHelper)slime.getMoveHelper()).setSpeed(1.2D);
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
        	((SlimeKingMoveHelper)slime.getMoveHelper()).setSpeed(1.0D);
        }
    }

}
