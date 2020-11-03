package com.kilkags.touchecho;

import com.google.common.collect.Maps;
import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.client.entity.slimeking.RenderSlimeKing;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.entity.ModEntityList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ModItemList;
import com.kilkags.touchecho.lotus.LotusSymphony;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Map;
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
        String registryName;

        for(Map.Entry<Entity, Object[]> entry: ModEntityList.ENTITY_MAP.entrySet()) {
            registryName = entry.getValue()[0].toString();

            if(entry.getKey() instanceof EntityMob) {
                event.getRegistry().register(EntityEntryBuilder.create()
                        .entity(entry.getKey().getClass())
                        .id(registryName, 0)
                        .name(LotusSymphony.getLangKeyFromRegKey(registryName))
                        .tracker(80, 3, true).build());
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerItemModelAll(ModItemList.ITEM_LIST_MAP.keySet().toArray(new Item[0]));
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Item item) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory");
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
    }
}
