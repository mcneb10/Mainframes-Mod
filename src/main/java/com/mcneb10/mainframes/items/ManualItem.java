package com.mcneb10.mainframes.items;

import com.mcneb10.mainframes.MainModClass;
import com.mcneb10.mainframes.gui.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ManualItem extends BaseItem {

	public ManualItem(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		//if(!worldIn.isRemote) {
			playerIn.openGui(MainModClass.instance, GuiHandler.MANUAL, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
		//}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
}
