package com.kilkags.touchecho.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class CapabilityRegistryHandler {
    @CapabilityInject(DirtBallPower.class)
    public static Capability<DirtBallPower> DIRT_BALL_POWER = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(DirtBallPower.class, new Capability.IStorage<DirtBallPower>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<DirtBallPower> capability, DirtBallPower instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<DirtBallPower> capability, DirtBallPower instance, EnumFacing side, NBTBase nbt) {
                if(nbt instanceof NBTTagCompound) {
                    instance.deserializeNBT((NBTTagCompound) nbt);
                }
            }
        }, DirtBallPower::new);
    }
}
