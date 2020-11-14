package com.kilkags.touchecho.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class TouchEchoGuiHandler implements IGuiHandler {
    private static int id = 0;
    public static final int DIRT_COMPRESSOR = id++;
    public static final int FLUID_TANK = id++;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == DIRT_COMPRESSOR) {
            return new ContainerDirtCompressor(player, world, x, y, z);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == DIRT_COMPRESSOR) {
            return new GuiDirtCompressor(player, world, x, y, z);
        }
        return null;
    }
}
