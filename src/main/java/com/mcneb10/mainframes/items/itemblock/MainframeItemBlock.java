package com.mcneb10.mainframes.items.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class MainframeItemBlock extends ItemBlock {

	public MainframeItemBlock(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}
	/*
	   @Override
	   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
	   	super.addInformation(stack, playerIn, tooltip, advanced);
	   	NBTTagCompound nbt;
	   	if(stack.hasTagCompound()) {
	   		nbt = stack.getTagCompound();
	   	} else {
	   		nbt = new NBTTagCompound();
	   	}
	   	if(nbt.hasKey("name")) {
	   		tooltip.add("Name: "+nbt.getString("name"));
	   	} else {
	   		String id = Utils.getInstance().generateNewMainframeName();
	   		nbt.setString("name", id);
	   		tooltip.add("Name: "+id);
	   	}
	   	stack.setTagCompound(nbt);
	   }*/
	
}
