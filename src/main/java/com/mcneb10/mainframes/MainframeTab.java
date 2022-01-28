package com.mcneb10.mainframes;

import com.mcneb10.mainframes.blocks.ModBlocks;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public class MainframeTab extends CreativeTabs {

	public MainframeTab(int index, String label) {
		super(index, label);
	}
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ModBlocks.spool);
	}
	@Override
	public String getTranslatedTabLabel() {
		// TODO Auto-generated method stub
		return I18n.format("itemGroup.mainframetab", TextFormatting.DARK_RED, TextFormatting.RESET);
	}
}
