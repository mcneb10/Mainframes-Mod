package com.mcneb10.mainframes.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DiskDriveBlock extends BaseBlock {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool LOADED = PropertyBool.create("loaded");
	
	public DiskDriveBlock(String name) {
		super(Material.ANVIL,name);
		this.setHardness(10);
		this.setResistance(500);
		this.setSoundType(SoundType.ANVIL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LOADED, false));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		// TODO Auto-generated method stub
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, stack).withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(LOADED, false);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return state.getValue(FACING).getHorizontalIndex() | (state.getValue(LOADED).booleanValue() ? 4 : 0);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(LOADED, (meta & 4)==4);
	}
	@Override
	protected BlockStateContainer createBlockState() {
		// TODO Auto-generated method stub
		return new BlockStateContainer(this, FACING, LOADED);
	}

}
