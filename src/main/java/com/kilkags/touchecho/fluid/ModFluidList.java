package com.kilkags.touchecho.fluid;

import com.kilkags.touchecho.toolkits.Types;
import net.minecraftforge.fluids.Fluid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class ModFluidList {
    public static final List<Fluid> FLUID_LIST = new ArrayList<>();

    public static final FluidCommon FAKE_WATER = new FluidCommon("fake_water", 1000, 1000, 0, 300, false, Types.Color.PURE_BLUE);
}
