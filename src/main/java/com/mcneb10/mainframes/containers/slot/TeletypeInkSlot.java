package com.mcneb10.mainframes.containers.slot;

import java.util.function.Consumer;

import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TeletypeInkSlot extends SlotItemHandler {

	public TeletypeInkSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		OreDictionary.getOres("dye").forEach(new Consumer<ItemStack>() {

			@Override
			public void accept(ItemStack t) {
				System.out.print("Item unlocalized name: "+t.getItem().getUnlocalizedName());
			}
			
		});
		return stack.getItem() instanceof ItemDye || OreDictionary.getOres("dye").contains(stack);
	}
	
	@Override
   	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

}
