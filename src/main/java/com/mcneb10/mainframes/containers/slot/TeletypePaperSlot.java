package com.mcneb10.mainframes.containers.slot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TeletypePaperSlot extends SlotItemHandler {

	public TeletypePaperSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == Items.PAPER;
	}
	
	@Override
   	public int getItemStackLimit(ItemStack stack) {
		return 64;
	}

}
