package com.mcneb10.mainframes.items;

import com.mcneb10.mainframes.CONSTS;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	public static Item reel;
	public static void preInit() {
		reel = new ReelItem("reel");
		registerItem(reel, "reel");
	}
	public static void registerItem(Item item, String name) {
		GameRegistry.register(item, new ResourceLocation(CONSTS.MODID, name));
	}
	public static void registerRenders() {
		registerRender(reel);
	}
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(CONSTS.MODID+":"+item.getUnlocalizedName().substring(5), "inventory"));
	}
}
