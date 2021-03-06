package com.mcneb10.mainframes.tileentities;

import com.mcneb10.mainframes.blocks.SpoolBlock;
import com.mcneb10.mainframes.items.ReelItem;

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

public class TileEntitySpool extends TileEntity implements ICapabilityProvider, ITickable{
	private ItemStackHandler handler;
	private boolean loaded = false;
	public TileEntitySpool() {
		this.handler = new ItemStackHandler(1);
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ISH"));
		this.loaded = compound.getBoolean("LOADEDB");
		super.readFromNBT(compound);
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("ISH", this.handler.serializeNBT());
		compound.setBoolean("LOADEDB", this.loaded);
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
			if (this.loaded) return;
			//something is in the slot
			if(this.handler.getStackInSlot(0).getItem() instanceof ReelItem) {
				this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(SpoolBlock.LOADED, true));
				this.loaded=true;
				//play load sound
			}
		} else {
			if (!this.loaded) return;
			//nothing is in the slot
			this.getWorld().setBlockState(pos, this.getWorld().getBlockState(pos).withProperty(SpoolBlock.LOADED, false));
			this.loaded=false;
			//play unload sound
		}
	}
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}
}
