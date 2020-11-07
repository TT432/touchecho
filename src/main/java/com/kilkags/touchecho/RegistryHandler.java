package com.kilkags.touchecho;

import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.capability.DirtBallPower;
import com.kilkags.touchecho.capability.DirtBallPowerProvider;
import com.kilkags.touchecho.client.entity.slimeking.RenderSlimeKing;
import com.kilkags.touchecho.commond.ModCommandCommon;
import com.kilkags.touchecho.entity.EntityDirtBall;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.entity.ModEntityList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItemList.ITEM_LIST_MAP.keySet().toArray(new Item[0]));

        for(Item item : ModItemList.ITEM_LIST_MAP.keySet()) {
            if(item instanceof IHasJson) {
                ((IHasJson) item).whitJson();
            }
        }
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlockList.BLOCK_MAP.keySet().toArray(new Block[0]));

        for(Block block : ModBlockList.BLOCK_MAP.keySet()) {
            if(block instanceof IHasJson) {
                ((IHasJson) block).whitJson();
            }
        }
    }

    @SubscribeEvent
    public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event) {
        ModEntityList.initEntityMap();

        event.getRegistry().registerAll(ModEntityList.ENTITY_MAP.keySet().toArray(new EntityEntry[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerItemModelAll(ModItemList.ITEM_LIST_MAP.keySet().toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer) {
            DirtBallPowerProvider provider = new DirtBallPowerProvider();
            event.addCapability(new ResourceLocation(TouchEcho.MOD_ID + ":dirt_ball_power"), provider);
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

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Item item) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModelAll(Item... items) {
        for(Item item : items) {
            registerItemModel(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenderAll() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySlimeKing.class, RenderSlimeKing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDirtBall.class,
                manager -> new RenderSnowball<>(manager, ModItemList.DIRT_BALL, Minecraft.getMinecraft().getRenderItem()));
    }

    public static void registerCommand(FMLServerStartingEvent event) {
        event.registerServerCommand(new ModCommandCommon());
    }
}
