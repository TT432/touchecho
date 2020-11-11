package com.kilkags.touchecho.interfaces;

import net.minecraftforge.fluids.BlockFluidBase;

/**
 * @author DustW
 */
public interface IHasBlock {
    /**
     * default:
     *      FLUID_BLOCK = new FluidBlockCommon(name, this);
     */
    void withBlock();

    /**
     * 获取流体的方块
     * @return 流体方块
     */
    BlockFluidBase getFluidBlock();
}
