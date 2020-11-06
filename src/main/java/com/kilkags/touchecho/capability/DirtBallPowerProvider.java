package com.kilkags.touchecho.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class DirtBallPowerProvider implements ICapabilitySerializable<NBTTagCompound> {
    private final DirtBallPower instance;
    private final Capability<DirtBallPower> capability;

    public DirtBallPowerProvider() {
        this.instance = new DirtBallPower();
        this.capability = CapabilityRegistryHandler.DIRT_BALL_POWER;
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return this.capability.equals(capability);
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.capability.equals(capability) ? this.capability.cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return this.instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.instance.deserializeNBT(nbt);
    }
}
