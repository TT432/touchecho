package com.kilkags.touchecho.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class ModItemList {
    public static final Map<Item, Object[]> ITEM_LIST_MAP = new HashMap<>();

    public static final ItemCommon DIRT_BALL = new ItemCommon("dirt_ball", 16);
    public static final ItemToolCommon DIRT_TOOL = new ItemToolCommon("dirt_tool", 1, 44, 3.0F, 1.0F, 5);
    public static final ItemArmorCommon DIRT_ARMOR = new ItemArmorCommon("dirt_armor",5, new int[]{1, 2, 2, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
}
