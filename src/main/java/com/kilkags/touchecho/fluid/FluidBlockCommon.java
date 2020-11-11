package com.kilkags.touchecho.fluid;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.item.ModItemList;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Objects;

/**
 * @author DustW
 */
public class FluidBlockCommon extends BlockFluidClassic {
    public FluidBlockCommon(String name, Fluid fluid) {
        super(fluid, Material.WATER);
        this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
        this.setRegistryName(name);

        int color = fluid.getColor();

        ModBlockList.BLOCK_MAP.put(this, new Object[] {color});
        ModItemList.ITEM_LIST_MAP.put(new ItemBlock(this).setRegistryName(name), new Object[] {});
    }
}
