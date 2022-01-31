package com.mcneb10.mainframes.items;

import com.mcneb10.mainframes.CONSTS;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static Item reel;
	public static Item disk;
	public static Item manual;
	public static Item debugger;
	public static void preInit() {
		reel = new ReelItem("reel");
		disk = new DiskItem("disk");
		manual = new ManualItem("manual");
		debugger = new Debugger("debugger");
		registerItem(reel, "reel");
		registerItem(disk, "disk");
		registerItem(manual, "manual");
		registerItem(debugger, "debugger");
	}
	public static void registerItem(Item item, String name) {
		GameRegistry.register(item, new ResourceLocation(CONSTS.MODID, name));
	}
	public static void registerRenders() {
		registerRender(reel);
		registerRender(disk);
		registerRender(manual);
		registerRender(debugger);
	}
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(CONSTS.MODID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}
}
