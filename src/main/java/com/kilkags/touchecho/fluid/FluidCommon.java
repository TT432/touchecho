package com.kilkags.touchecho.fluid;

import com.kilkags.touchecho.interfaces.IHasBlock;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import com.kilkags.touchecho.toolkits.Types;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * @author DustW
 *      setDensity       default : 1000(water)  方法用于设置这个流体的密度，单位为千克每立方米
 *      setViscosity     default : 1000(water)  方法用于设置这个流体的粘度，单位为千分之一平方米每秒，使用运动粘度
 *      setLuminosity    default : 0(water)     方法用于设置这个流体的亮度，也就是在Minecraft中的亮度
 *      setTemperature   default : 300(室温)     方法用于设置这个流体的温度，使用热力学温标，也就是开尔文
 *      setGaseous       default : false(water) 方法用于标注这个流体是否为气体，默认不是
 */
public class FluidCommon extends Fluid implements IHasBlock {
    private BlockFluidClassic blockFluid;
    private final String NAME;

    public FluidCommon(String name, int density, int viscosity, int luminosity, int temperature, boolean isGaseous, int colorIn) {
        super(name + "fluid", Types.Paths.getFluidTexturePath(name)[0], Types.Paths.getFluidTexturePath(name)[1]);

        this.NAME = name;
        this.setUnlocalizedName(LotusSymphony.getLangKeyFromRegKey(name));

        int color = colorIn | 0xFF000000;
        this.setColor(color);
        this.setDensity(density);
        this.setViscosity(viscosity);
        this.setLuminosity(luminosity);
        this.setTemperature(temperature);
        this.setGaseous(isGaseous);

        ModFluidList.FLUID_LIST.add(this);
    }

    /**
     * default:
     * FLUID_BLOCK = new FluidBlockCommon(name, this);
     */
    @Override
    public void withBlock() {
        blockFluid = new FluidBlockCommon(this.NAME, this);
    }

    /**
     * 获取流体的方块
     *
     * @return 流体方块
     */
    @Override
    public BlockFluidBase getFluidBlock() {
        return this.blockFluid;
    }
}
