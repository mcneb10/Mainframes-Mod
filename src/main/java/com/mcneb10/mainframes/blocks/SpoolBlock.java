package com.mcneb10.mainframes.blocks;

import com.mcneb10.mainframes.MainModClass;
import com.mcneb10.mainframes.gui.GuiHandler;
import com.mcneb10.mainframes.tileentities.TileEntitySpool;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SpoolBlock extends BaseBlock implements ITileEntityProvider {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool LOADED = PropertyBool.create("loaded");
	public static final AxisAlignedBB SPOOL_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.75D, 1D);
	
	public SpoolBlock(String name) {
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
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return new TileEntitySpool();
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
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		
		return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.spoolbase;
	}
	   @Override
	   public boolean isOpaqueCube(IBlockState state) {
	   	// TODO Auto-generated method stub
	   	return false;
	   }
	      @Override
	   public boolean isFullCube(IBlockState state) {
	   	// TODO Auto-generated method stub
	   	return false;
	   }
	      @Override
	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
	    	// TODO Auto-generated method stub
	    	return SPOOL_AABB;
	    }

		@Override
		public TileEntity createNewTileEntity(World worldIn, int meta) {

			return new TileEntitySpool();
		}
		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
			TileEntitySpool tileent = (TileEntitySpool)worldIn.getTileEntity(pos);
			IItemHandler hand = tileent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if (hand.getStackInSlot(0)!=null)InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), hand.getStackInSlot(0));
			super.breakBlock(worldIn, pos, state);
		}
		@Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
				EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
			if(!worldIn.isRemote) {
				playerIn.openGui(MainModClass.instance, GuiHandler.SPOOL, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			
			return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		}
}
