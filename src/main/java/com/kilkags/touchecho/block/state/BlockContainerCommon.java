package com.kilkags.touchecho.block.state;

import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ModItemList;
import com.kilkags.touchecho.toolkits.AutoJson;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author DustW
 */
public abstract class BlockContainerCommon extends BlockContainer {
    private static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing");

    public BlockContainerCommon(Material material, String name, String toolType, int blockLevel, float hardness) {
        super(material);
        this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
        this.setRegistryName(name);
        this.setHarvestLevel(toolType, blockLevel);
        this.setHardness(hardness);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));

        this.setCreativeTab(ModTabsList.MACHINE_TAB);

        ModBlockList.BLOCK_MAP.put(this, new Object[]{});
        ModItemList.ITEM_LIST_MAP.put(new ItemBlock(this).setRegistryName(this.getRegistryName()), new Object[]{});
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withProperty(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }
}
