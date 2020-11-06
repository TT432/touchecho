package com.kilkags.touchecho.entity;

import com.kilkags.touchecho.potion.PotionRegistryHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 */
public class EntityDirtBall extends EntityThrowable {
    public static final String ENTITY_DIRT_BALL = "dirt_ball";

    public EntityDirtBall(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityDirtBall(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void onImpact(@NotNull RayTraceResult result) {
        if(!this.world.isRemote) {
            if(result.entityHit != null) {
                float amount = 6F;
                DamageSource source = DamageSource.causeThrownDamage(this, this.getThrower());
                if(result.entityHit instanceof  EntityLivingBase) {
                    EntityLivingBase target = ((EntityLivingBase) result.entityHit);
                    if(target.isPotionActive(PotionRegistryHandler.POTION_DIRT_PROTECTION)) {
                        PotionEffect effect = target.getActivePotionEffect(PotionRegistryHandler.POTION_DIRT_PROTECTION);
                        amount = effect.getAmplifier() > 0 ? 0 : amount / 2;
                    }
                }
                result.entityHit.attackEntityFrom(source, amount);
            }
            this.setDead();
        }
    }
}
