package com.kilkags.touchecho.fluid;

/**
 * @author DustW
 */
public class FluidWithBlockCommon {
    public final FluidCommon FLUID;
    public final FluidBlockCommon FLUID_BLOCK;

    public FluidWithBlockCommon(String name, int density, int viscosity, int luminosity, int temperature, boolean isGaseous, int colorIn) {
        FLUID = new FluidCommon(name + "_fluid", density, viscosity, luminosity, temperature, isGaseous, colorIn);
        FLUID_BLOCK = new FluidBlockCommon(name, FLUID);
    }
}
