package com.mcneb10.mainframes.containers.slot;

import com.mcneb10.mainframes.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SpoolSlot extends SlotItemHandler {

	public SpoolSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == ModItems.reel;
	}
	
	@Override
   	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

}
