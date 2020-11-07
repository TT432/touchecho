package com.kilkags.touchecho;

import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.capability.DirtBallPower;
import com.kilkags.touchecho.enchantment.EnchantmentRegistryHandler;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.lotus.Types;
import com.kilkags.touchecho.potion.PotionRegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * @author DustW
 */
public class EventHandler {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getImmediateSource();
        if(source instanceof EntityPlayer && !source.world.isRemote) {
            EntityPlayer player = (EntityPlayer) source;
            ItemStack heldItemMainHand = player.getHeldItemMainhand();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistryHandler.EXPLOSION, heldItemMainHand);
            if(level > 0) {
                Entity target = event.getEntity();
                target.world.createExplosion(null, target.posX, target.posY, target.posZ, 2 * level, false);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        if(Types.DamageSources.FALL.equals(damageSource.getDamageType())) {
            EntityLivingBase target = event.getEntityLiving();
            Potion potion = PotionRegistryHandler.POTION_DIRT_PROTECTION;
            if(target.isPotionActive(potion)) {
                PotionEffect effect = target.getActivePotionEffect(potion);
                BlockPos pos = new BlockPos(target.posX, target.posY - 0.2, target.posZ);
                Block block = target.world.getBlockState(pos).getBlock();
                if(block == Blocks.DIRT || block == Blocks.GRASS) {
                    event.setAmount(effect.getAmplifier() > 0 ? 0 : event.getAmount() / 2);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if(entity instanceof EntitySlimeKing) {
            float amount = Math.min(entity.getHealth(), event.getAmount());
            Entity source = entity.getLastDamageSource().getTrueSource();
            if(source instanceof EntityPlayer) {
                DirtBallPower power = source.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
                TextComponentString text = addPower(power, amount);
                source.sendMessage(text);
            }
        }
    }

    private static TextComponentString addPower(DirtBallPower power, float amount) {
        switch(TouchEcho.RANDOM.nextInt(3) + 1){
            case 1:
                power.setOrangePower(power.getOrangePower() + amount);
                return new TextComponentString("Orange power + " + amount);
            case 2:
                power.setGreenPower(power.getGreenPower() + amount);
                return new TextComponentString("Green power + " + amount);
            case 3:
                power.setBluePower(power.getBluePower() + amount);
                return new TextComponentString("Blue power + " + amount);
            default:
                return new TextComponentString("出现错误");
        }
    }
}

