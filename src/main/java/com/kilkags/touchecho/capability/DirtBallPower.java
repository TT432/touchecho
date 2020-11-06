package com.kilkags.touchecho.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author DustW
 */
public class DirtBallPower implements INBTSerializable<NBTTagCompound> {
    private float orangePower;
    private float greenPower;
    private float bluePower;

    public DirtBallPower() {
        this.orangePower = 0.0F;
        this.bluePower = 0.0F;
        this.greenPower = 0.0F;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setFloat("Blue", this.getBluePower());
        nbt.setFloat("Green", this.getGreenPower());
        nbt.setFloat("Orange", this.getOrangePower());

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.orangePower = nbt.getFloat("Orange");
        this.greenPower = nbt.getFloat("Green");
        this.bluePower = nbt.getFloat("blue");
    }

    public void setOrangePower(float orangePower) { this.orangePower = orangePower; }

    public void setGreenPower(float greenPower) { this.greenPower = greenPower; }

    public void setBluePower(float bluePower) { this.bluePower = bluePower; }

    public float getOrangePower() { return orangePower; }

    public float getGreenPower() { return greenPower; }

    public float getBluePower() { return bluePower; }

}
