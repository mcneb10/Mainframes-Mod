package com.mcneb10.mainframes.blocks;

import java.util.List;

import com.mcneb10.mainframes.MainModClass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BaseBlock extends Block {

	public BaseBlock(Material blockMaterialIn, String name) {
		super(blockMaterialIn);
		this.setUnlocalizedName(name);
		this.setCreativeTab(MainModClass.tab);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(!I18n.hasKey("tooltip."+this.getUnlocalizedName().substring(5))) return;
		for(String line:I18n.format("tooltip."+this.getUnlocalizedName().substring(5)).split("\n")) {
			tooltip.add(line);
		}
	}
}
