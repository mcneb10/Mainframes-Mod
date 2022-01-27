package com.mcneb10.mainframes.tileentities;

import com.mcneb10.mainframes.blocks.DiskDriveBlock;
import com.mcneb10.mainframes.items.DiskItem;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDiskDrive extends TileEntity implements ICapabilityProvider, ITickable{
	private ItemStackHandler handler;
	public TileEntityDiskDrive() {
		this.handler = new ItemStackHandler(1);
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ISH"));
		super.readFromNBT(compound);
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("ISH", this.handler.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		this.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	@Override
	public void update() {
		
		if(this.handler.getStackInSlot(0)!=null) {
			//something is in the slot
			if(this.handler.getStackInSlot(0).getItem() instanceof DiskItem) this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(DiskDriveBlock.LOADED, true));
		} else {
			//nothing is in the slot
			this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(DiskDriveBlock.LOADED, false));
		}
	}
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}
}
