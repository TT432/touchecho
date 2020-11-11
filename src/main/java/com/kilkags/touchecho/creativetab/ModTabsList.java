package com.kilkags.touchecho.creativetab;

import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTabsList {
    public static final CreativeTabs ARMOR_TAB = new CreativeTabs("armor_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItemList.DIRT_ARMOR.CHEST);
        }
    };

    public static final CreativeTabs BLOCK_TAB = new CreativeTabs("block_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlockList.BLOCK_COMPRESSED_DIRT);
        }
    };

    public static final CreativeTabs ITEM_TAB = new CreativeTabs("item_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItemList.DIRT_BALL);
        }
    };

    public static final CreativeTabs TOOL_TAB = new CreativeTabs("tool_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItemList.DIRT_TOOL.PICKAXE);
        }
    };

    public static final CreativeTabs MACHINE_TAB = new CreativeTabs("machine_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlockList.BLOCK_DIRT_COMPRESSOR);
        }
    };
}
