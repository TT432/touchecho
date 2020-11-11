package com.kilkags.touchecho.item;

import com.google.common.base.CaseFormat;
import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.toolkits.AutoJson;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import com.kilkags.touchecho.toolkits.Types;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author DustW
 */
public class ItemToolCommon {
    public final ItemSword SWORD;
    public final ItemAxe AXE;
    public final ItemPickaxe PICKAXE;
    public final ItemSpade SHOVEL;
    public final ItemHoe HOE;

    public static class PickaxeCommon extends ItemPickaxe implements IHasJson {
        protected PickaxeCommon(String name, ToolMaterial material) {
            super(material);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
            this.setRegistryName(name);

            this.setCreativeTab(ModTabsList.TOOL_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * new JsonItemCreator(this.getRegistryName(), Types.JsonTypes.?);
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.HANDHELD);
        }
    }

    public static class AxeCommon extends ItemAxe implements IHasJson {
        protected AxeCommon(String name, ToolMaterial material) {
            super(material, material.getAttackDamage() + 4, 0.8F);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
            this.setRegistryName(name);

            this.setCreativeTab(ModTabsList.TOOL_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);//工具
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.HANDHELD);
        }
    }

    public static class ShovelCommon extends ItemSpade implements IHasJson {
        public ShovelCommon(String name, ToolMaterial material) {
            super(material);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
            this.setRegistryName(name);

            this.setCreativeTab(ModTabsList.TOOL_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);//工具
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.HANDHELD);
        }
    }

    public static class SwordCommon extends ItemSword implements IHasJson {
        public SwordCommon(String name, ToolMaterial material) {
            super(material);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
            this.setRegistryName(name);

            this.setCreativeTab(ModTabsList.TOOL_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);//工具
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.HANDHELD);
        }
    }

    public static class HoeCommon extends ItemHoe implements IHasJson {
        public HoeCommon(String name, ToolMaterial material) {
            super(material);
            this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
            this.setRegistryName(name);

            this.setCreativeTab(ModTabsList.TOOL_TAB);

            ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
        }

        /**
         * default:
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);//工具
         * new JsonItemCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);//物品
         */
        @Override
        public void whitJson() {
            AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.HANDHELD);
        }
    }

    public static Item.ToolMaterial toolMaterialCreator(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantAbility) {
        return EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantAbility);
    }

    public ItemToolCommon(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantAbility) {
        Item.ToolMaterial toolMaterial = toolMaterialCreator(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, name), harvestLevel, maxUses, efficiency, damage, enchantAbility);
        SWORD = new SwordCommon(name + "_sword", toolMaterial);
        AXE = new AxeCommon(name + "_axe", toolMaterial);
        PICKAXE = new PickaxeCommon(name + "_pickaxe", toolMaterial);
        SHOVEL = new ShovelCommon(name + "_shovel", toolMaterial);
        HOE = new HoeCommon(name + "_hoe", toolMaterial);
    }
}
