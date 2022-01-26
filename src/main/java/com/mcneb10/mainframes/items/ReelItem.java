package com.mcneb10.mainframes.items;

import java.util.List;

import com.mcneb10.mainframes.MainModClass;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReelItem extends BaseItem {

	public ReelItem(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.bFull3D=true;
	}

	
}
