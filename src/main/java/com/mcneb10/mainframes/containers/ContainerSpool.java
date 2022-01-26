package com.mcneb10.mainframes.containers;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mcneb10.mainframes.blocks.SpoolBlock;
import com.mcneb10.mainframes.containers.slot.SpoolSlot;
import com.mcneb10.mainframes.tileentities.TileEntitySpool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSpool extends Container {
	private TileEntitySpool tileentity;
	private IItemHandler handler;
	public ContainerSpool(IInventory playerInv, TileEntitySpool te) {
		this.tileentity = te;
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SpoolSlot(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 80, 35));
		int xPos = 8;
		int yPos = 84;
				
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}
				
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
		/*this.addListener(new IContainerListener() {
			
			
			
			@Override
			public void updateCraftingInventory(Container containerToSend, List<ItemStack> itemsList) {
				if(itemsList.size() > 0) {
					if(itemsList.get(0) != null) {
						tileentity.getWorld().setBlockState(tileentity.getPos(), tileentity.getWorld().getBlockState(tileentity.getPos()).withProperty(SpoolBlock.LOADED, itemsList.get(0).stackSize>0));
						return;
					}
				}
				tileentity.getWorld().setBlockState(tileentity.getPos(), tileentity.getWorld().getBlockState(tileentity.getPos()).withProperty(SpoolBlock.LOADED, false));
			}
			
			@Override
			public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {

			}
			
			@Override
			public void sendProgressBarUpdate(Container containerIn, int varToUpdate, int newValue) {

			}
			
			@Override
			public void sendAllWindowProperties(Container containerIn, IInventory inventory) {

			}
		});*/
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return !playerIn.isSpectator();
	}
	 @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	    {
	        ItemStack itemstack = null;
	        Slot slot = (Slot)this.inventorySlots.get(index);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (index < this.handler.getSlots())
	            {
	                if (!this.mergeItemStack(itemstack1, this.handler.getSlots(), this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 0, handler.getSlots(), false))
	            {
	                return null;
	            }

	            if (itemstack1.stackSize == 0)
	            {
	                slot.putStack((ItemStack)null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }
	        }

	        return itemstack;
	    }
	 @Override
	 public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		 ItemStack result = super.slotClick(slotId, dragType, clickTypeIn, player);
         if(!tileentity.getWorld().isRemote) tileentity.getWorld().setBlockState(tileentity.getPos(), tileentity.getWorld().getBlockState(tileentity.getPos()).withProperty(SpoolBlock.LOADED, new Random().nextBoolean()));
         //TODO: Dirty hack
         tileentity.getWorld().removeTileEntity(tileentity.getPos());
         tileentity.getWorld().addTileEntity(tileentity);
		 return result;
	 }
}
