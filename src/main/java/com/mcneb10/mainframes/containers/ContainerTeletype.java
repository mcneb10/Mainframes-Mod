package com.mcneb10.mainframes.containers;

import com.mcneb10.mainframes.containers.slot.TeletypeInkSlot;
import com.mcneb10.mainframes.containers.slot.TeletypePaperSlot;
import com.mcneb10.mainframes.tileentities.TileEntityTeletype;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerTeletype extends Container {
	//private TileEntityTeletype tileentity;
	private IItemHandler handler;
	public ContainerTeletype(IInventory playerInv, TileEntityTeletype te) {
		//this.tileentity = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new TeletypePaperSlot(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 146, 16));
		this.addSlotToContainer(new TeletypeInkSlot(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null),1,146,36));
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

}
