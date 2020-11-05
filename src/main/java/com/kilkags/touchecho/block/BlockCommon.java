package com.kilkags.touchecho.block;

import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ModItemList;
import com.kilkags.touchecho.lotus.AutoJson;
import com.kilkags.touchecho.lotus.LotusSymphony;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * @author DustW
 */
public class BlockCommon extends Block implements IHasJson {
    public BlockCommon(Material material, String name, String toolType, int blockLevel,float hardness) {
        super(material);
        this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
        this.setRegistryName(name);
        this.setHarvestLevel(toolType, blockLevel);
        this.setHardness(hardness);

        this.setCreativeTab(ModTabsList.BLOCK_TAB);

        ModBlockList.BLOCK_MAP.put(this, new Object[]{});
        ModItemList.ITEM_LIST_MAP.put(new ItemBlock(this).setRegistryName(this.getRegistryName()), new Object[]{});
    }

    /**
     * default:
     * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);;//工具
     * AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);;//物品
     */
    @Override
    public void whitJson() {
        AutoJson.blockCommonJsonCreator(this.getRegistryName().getPath());
    }
}
