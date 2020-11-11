package com.kilkags.touchecho.block;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.block.state.BlockContainerCommon;
import com.kilkags.touchecho.toolkits.Types;
import com.kilkags.touchecho.network.TouchEchoGuiHandler;
import com.kilkags.touchecho.tileentity.TileEntityDirtCompressor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class ModBlockList {
    public static final Map<Block, Object[]> BLOCK_MAP = new HashMap<>();

    public static final BlockCommon BLOCK_COMPRESSED_DIRT = new BlockCommon(Material.GRASS, "compressed_dirt", Types.Tools.SHOVEL, 1, 0.5F);

    public static final Block BLOCK_DIRT_COMPRESSOR = new BlockContainerCommon(Material.ROCK, "dirt_compressor", Types.Tools.PICKAXE, 1, 3.5F){
        @Nullable
        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TileEntityDirtCompressor();
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            if(!worldIn.isRemote) {
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                playerIn.openGui(TouchEcho.MOD_ID, TouchEchoGuiHandler.DIRT_COMPRESSOR, worldIn, x, y, z);
            }
            return true;
        }

        @Override
        public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

            IItemHandler up = tileEntity.getCapability(itemHandlerCapability, EnumFacing.UP);
            IItemHandler down = tileEntity.getCapability(itemHandlerCapability, EnumFacing.DOWN);
            IItemHandler side = tileEntity.getCapability(itemHandlerCapability, EnumFacing.NORTH);

            Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(0));
            Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(0));
            Block.spawnAsEntity(worldIn, pos, side.getStackInSlot(0));

            super.breakBlock(worldIn, pos, state);
        }

        @Override
        public EnumBlockRenderType getRenderType(IBlockState state) {
            return EnumBlockRenderType.MODEL;
        }
    };
}
