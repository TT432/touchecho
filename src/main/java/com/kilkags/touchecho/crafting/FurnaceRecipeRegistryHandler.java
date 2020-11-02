package com.kilkags.touchecho.crafting;

import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class FurnaceRecipeRegistryHandler {
    public static void register() {
        GameRegistry.addSmelting(ModItemList.DIRT_TOOL.PICKAXE, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_TOOL.AXE, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_TOOL.HOE, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_TOOL.SHOVEL, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_TOOL.SWORD, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_ARMOR.HEAD, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_ARMOR.CHEST, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_ARMOR.FEET, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
        GameRegistry.addSmelting(ModItemList.DIRT_ARMOR.LEGS, new ItemStack(ModItemList.DIRT_BALL), 0.1F);
    }

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        Item registerItem = event.getItemStack().getItem();
        if(registerItem == Item.getItemFromBlock(Blocks.DIRT)) {
            event.setBurnTime(400);
        }
    }
}
