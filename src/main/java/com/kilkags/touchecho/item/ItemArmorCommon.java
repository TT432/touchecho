package com.kilkags.touchecho.item;

import com.google.common.base.CaseFormat;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.lotus.AutoJson;
import com.kilkags.touchecho.lotus.LotusSymphony;
import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.lotus.Types;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmorCommon {
    public final ItemArmor HEAD;
    public final ItemArmor CHEST;
    public final ItemArmor LEGS;
    public final ItemArmor FEET;

    public static class ArmorHead extends ItemArmor implements IHasJson {
        public ArmorHead(String name, ArmorMaterial materialIn) {
            super(materialIn, 0, EntityEquipmentSlot.HEAD);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name) + "_head");
            this.setRegistryName(name + "_head");

            this.setCreativeTab(ModTabsList.ARMOR_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);;//工具
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);;//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);
        }
    }

    public static class ArmorChest extends ItemArmor implements IHasJson {
        public ArmorChest(String name, ArmorMaterial materialIn) {
            super(materialIn, 0, EntityEquipmentSlot.CHEST);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name) + "_chest");
            this.setRegistryName(name + "_chest");

            this.setCreativeTab(ModTabsList.ARMOR_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);;//工具
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);;//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);
        }
    }

    public static class ArmorLegs extends ItemArmor implements IHasJson {
        public ArmorLegs(String name, ArmorMaterial materialIn) {
            super(materialIn, 0, EntityEquipmentSlot.LEGS);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name) + "_legs");
            this.setRegistryName(name + "_legs");

            this.setCreativeTab(ModTabsList.ARMOR_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);;//工具
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);;//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);
        }
    }

    public static class ArmorFeet extends ItemArmor implements IHasJson {
        public ArmorFeet(String name, ArmorMaterial materialIn) {
            super(materialIn, 0, EntityEquipmentSlot.FEET);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name) + "_feet");
            this.setRegistryName(name + "_feet");

            this.setCreativeTab(ModTabsList.ARMOR_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);;//工具
         * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);;//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);
        }
    }

    public ItemArmor.ArmorMaterial armorMaterialCreator(String name, int durability, int[] armorNum, int enchantAbility, SoundEvent soundEvent, int toughness) {
        return EnumHelper.addArmorMaterial(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, name), LotusSymphony.getLangKeyFromRegKey(name), durability, armorNum, enchantAbility, soundEvent, toughness);
    }

    public ItemArmorCommon(String name, int durability, int[] armorNum, int enchantAbility, SoundEvent soundEvent, int toughness) {
        ItemArmor.ArmorMaterial material = armorMaterialCreator(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, name), durability, armorNum, enchantAbility, soundEvent, toughness);
        this.HEAD = new ArmorHead(name, material);
        this.CHEST = new ArmorChest(name, material);
        this.LEGS = new ArmorLegs(name, material);
        this.FEET = new ArmorFeet(name, material);
    }
}
