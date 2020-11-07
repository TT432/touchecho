package com.kilkags.touchecho;

import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.crafting.FurnaceRecipeRegistryHandler;
import com.kilkags.touchecho.potion.PotionRegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * @author kilkags
 */
@Mod(
        modid = TouchEcho.MOD_ID,
        name = TouchEcho.MOD_NAME,
        version = TouchEcho.VERSION
)
public class TouchEcho {

    public static final String MOD_ID = "touchecho";
    public static final String MOD_NAME = "Touchecho";
    public static final String VERSION = "1.0-SNAPSHOT";

    public static final Random RANDOM = new Random();

    @Mod.Instance(MOD_ID)
    public static TouchEcho INSTANCE;

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.registerRenderAll();
        CapabilityRegistryHandler.register();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FurnaceRecipeRegistryHandler.register();
        PotionRegistryHandler.registry();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        RegistryHandler.registerCommand(event);
    }
}
