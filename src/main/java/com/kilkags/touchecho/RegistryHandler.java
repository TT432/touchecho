package com.kilkags.touchecho;

import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ItemCommon;
import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

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
            ((IHasJson)item).whitJson();
        }
    }

    @SubscribeEvent
    public static void onRegistry(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlockList.BLOCK_MAP.keySet().toArray(new Block[0]));
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
}
