package com.mcneb10.mainframes.blocks;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableMap;
import com.mcneb10.mainframes.Utils;
import com.mcneb10.mainframes.tileentities.TileEntityMainframe;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MainframeBlock extends BaseBlock implements ITileEntityProvider {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool ON = PropertyBool.create("on");
	public static final AxisAlignedBB MAINFRAME_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1.6875D, 1D);
	public MainframeBlock(String name) {
		super(Material.ANVIL, name);
		this.setHardness(10);
		this.setResistance(500);
		this.setSoundType(SoundType.ANVIL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ON, false));
	}
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		// TODO Auto-generated method stub
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, stack).withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(ON, false);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return state.getValue(FACING).getHorizontalIndex() | (state.getValue(ON).booleanValue() ? 4 : 0);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(ON, (meta & 4)==4);
	}
	@Override
	protected BlockStateContainer createBlockState() {
		// TODO Auto-generated method stub
		return new BlockStateContainer(this, FACING, ON);
	}
   @Override
   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
		EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	   // TODO Auto-generated method stub
	   worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(ON, true));
	   return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
   }
   @Override
public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
	// TODO Auto-generated method stub
	return MAINFRAME_AABB;
}
   @Override
public boolean isOpaqueCube(IBlockState state) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public TileEntity createNewTileEntity(World worldIn, int meta) {
	return new TileEntityMainframe();
}
@Override
public TileEntity createTileEntity(World world, IBlockState state) {
	return new TileEntityMainframe();
}

}
