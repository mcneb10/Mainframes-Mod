package com.mcneb10.mainframes.blocks;

import com.mcneb10.mainframes.CONSTS;
import com.mcneb10.mainframes.items.itemblock.MainframeItemBlock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block spool;
	public static Block spoolbase;
	public static Block diskdrive;
	public static Block mainframe;
	public static Block teletype;
	public static void preInit() {
		spool = new SpoolBlock("spool");
		spoolbase = new SpoolBaseBlock("spoolbase");
		diskdrive = new DiskDriveBlock("diskdrive");
		mainframe = new MainframeBlock("mainframe");
		teletype = new TeletypeBlock("teletype");
		registerBlocks();
	}
	public static void registerBlocks() {
		registerBlock(spool, "spool");
		registerBlock(spoolbase, "spoolbase");
		registerBlock(diskdrive, "diskdrive");
		//registerBlock(mainframe, "mainframe");
		//register mainframe
		GameRegistry.register(mainframe, new ResourceLocation(CONSTS.MODID, "mainframe"));
		MainframeItemBlock ib = new MainframeItemBlock(mainframe);
		GameRegistry.register(ib, new ResourceLocation(CONSTS.MODID, "mainframe"));
		//end register mainframe
		registerBlock(teletype, "teletype");
	}
	public static void registerBlock(Block block, String name) {
		GameRegistry.register(block, new ResourceLocation(CONSTS.MODID, name));
		ItemBlock ib = new ItemBlock(block);
		GameRegistry.register(ib, new ResourceLocation(CONSTS.MODID, name));
	}
	public static void registerRenders() {
		registerRender(spool);
		registerRender(spoolbase);
		registerRender(diskdrive);
		registerRender(mainframe);
		registerRender(teletype);
	}
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(CONSTS.MODID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}
}
