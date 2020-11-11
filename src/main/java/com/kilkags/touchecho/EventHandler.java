package com.kilkags.touchecho;

import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.capability.DirtBallPower;
import com.kilkags.touchecho.enchantment.EnchantmentRegistryHandler;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.toolkits.Types;
import com.kilkags.touchecho.network.NetworkRegistryHandler;
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(!entity.world.isRemote && entity instanceof EntityPlayer) {
            /* 同步数据 */
            NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) entity);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        DirtBallPower instance = event.getEntityPlayer().getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
        DirtBallPower original = event.getOriginal().getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);

        assert instance != null;
        assert original != null;
        instance.setBluePower(original.getBluePower());
        instance.setGreenPower(original.getGreenPower());
        instance.setOrangePower(original.getOrangePower());
    }

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
            Entity source = event.getSource().getTrueSource();
            if(source instanceof EntityPlayer) {
                DirtBallPower power = source.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
                TextComponentTranslation text = addPower(power, amount);

                /* 同步数据 */
                NetworkRegistryHandler.Power.sendClientCustomPacket((EntityPlayer) source);

                source.sendMessage(text);
            }
        }
    }

    private static TextComponentTranslation addPower(DirtBallPower power, float amount) {
        switch(TouchEcho.RANDOM.nextInt(3) + 1){
            case 1:
                power.setOrangePower(power.getOrangePower() + amount);
                return new TextComponentTranslation("message.touchecho.power.add.orange", String.format("%.1f", amount));
            case 2:
                power.setGreenPower(power.getGreenPower() + amount);
                return new TextComponentTranslation("message.touchecho.power.add.green", String.format("%.1f", amount));
            case 3:
                power.setBluePower(power.getBluePower() + amount);
                return new TextComponentTranslation("message.touchecho.power.add.blue", String.format("%.1f", amount));
            default:
                return new TextComponentTranslation("message.touchecho.power.add.error");
        }
    }
}

