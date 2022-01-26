package com.mcneb10.mainframes.proxies;

import com.mcneb10.mainframes.MainModClass;
import com.mcneb10.mainframes.blocks.ModBlocks;
import com.mcneb10.mainframes.gui.GuiHandler;
import com.mcneb10.mainframes.items.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}
	@Override
	public void init(FMLInitializationEvent event) {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		NetworkRegistry.INSTANCE.registerGuiHandler(MainModClass.instance, new GuiHandler());
	}
	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}
}
