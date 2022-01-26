package com.mcneb10.mainframes.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySpool extends TileEntity implements ICapabilityProvider, ITickable{
	private ItemStackHandler handler;
	public TileEntitySpool() {
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
		/*
		if(this.handler.getStackInSlot(0)!=null&&this.getWorld().getBlockState(getPos()).getBlock().getClass()==SpoolBlock.class) {
			//something is in the slot
			if(!this.getWorld().isRemote) this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(SpoolBlock.LOADED, true));
		} else {
			//nothing is in the slot
			if(!this.getWorld().isRemote) this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(SpoolBlock.LOADED, false));
		}*/
	}
}
