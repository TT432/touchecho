package com.kilkags.touchecho.potion;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author DustW
 */
public class PotionRegistryHandler {
    public static final PotionDirtProtection POTION_DIRT_PROTECTION = new PotionDirtProtection();
    public static final PotionType POTION_TYPE_DIRT_PROTECTION =
            new PotionType(TouchEcho.MOD_ID + ".dirtProtection",
                    new PotionEffect(POTION_DIRT_PROTECTION, 1800, 1))
                    .setRegistryName("dirt_protection");

    @SubscribeEvent
    public static void onPotionRegistry(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();
        registry.register(POTION_DIRT_PROTECTION);
    }

    @SubscribeEvent
    public static void onPotionTypeRegistry(RegistryEvent.Register<PotionType> event) {
        event.getRegistry().register(POTION_TYPE_DIRT_PROTECTION);
    }

    public static void registry() {
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItemList.DIRT_BALL, POTION_TYPE_DIRT_PROTECTION);
    }
}
