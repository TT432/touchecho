package com.kilkags.touchecho;

import com.kilkags.touchecho.crafting.FurnaceRecipeRegistryHandler;
import com.kilkags.touchecho.entity.ModEntityList;
import com.kilkags.touchecho.potion.PotionRegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static TouchEcho INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.registerRenderAll();
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FurnaceRecipeRegistryHandler.register();
        PotionRegistryHandler.registry();
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
