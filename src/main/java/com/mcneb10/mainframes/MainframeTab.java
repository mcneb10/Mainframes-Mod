package com.mcneb10.mainframes;

import com.mcneb10.mainframes.blocks.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MainframeTab extends CreativeTabs {

	public MainframeTab(int index, String label) {
		super(index, label);
	}
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ModBlocks.spool);
	}
}
