package com.mcneb10.mainframes.items;

import com.mcneb10.mainframes.interfaces.IPipeProviderIndicator;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class Debugger extends BaseItem {

	public Debugger(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				Block block = worldIn.getBlockState(pos).getBlock();
				if (block instanceof IPipeProviderIndicator) {
					if (((IPipeProviderIndicator) block).getPipeProvider().getLatestPipe("error") == null) {
						playerIn.addChatMessage(new TextComponentString(
								I18n.format("menu.debugger.noerror", TextFormatting.GREEN, TextFormatting.RESET)));
					} else {
						playerIn.addChatMessage(new TextComponentString(
								I18n.format("menu.debugger.error", TextFormatting.RED, TextFormatting.RESET,
										((IPipeProviderIndicator) block).getPipeProvider().getLatestPipe("error"))));
					}
				} else {
					playerIn.addChatMessage(new TextComponentString(
							I18n.format("menu.debugger.noerror", TextFormatting.GREEN, TextFormatting.RESET)));
				}
			}
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
}
