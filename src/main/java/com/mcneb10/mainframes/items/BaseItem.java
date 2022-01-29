package com.mcneb10.mainframes.items;

import java.util.List;

import com.mcneb10.mainframes.MainModClass;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseItem extends Item {
public BaseItem(String name) {
	this.setUnlocalizedName(name);
	this.setCreativeTab(MainModClass.tab);
}
@Override
public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
	for(String line:I18n.format("tooltip."+this.getUnlocalizedName().substring(5)).split("\n")) {
		tooltip.add(line);
	}		
}

}
