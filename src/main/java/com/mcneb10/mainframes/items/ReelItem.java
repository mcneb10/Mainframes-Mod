package com.mcneb10.mainframes.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ReelItem extends BaseItem {

	public ReelItem(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.bFull3D = true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("ID")) {
			tooltip.add("ID: "+nbt.getString("ID"));
		} else {
			String id = "REEL-"+UUID.randomUUID().toString();
			nbt.setString("ID", id);
			tooltip.add("ID: "+id);
		}
		stack.setTagCompound(nbt);
	}
}
