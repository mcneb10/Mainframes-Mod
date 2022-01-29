package com.mcneb10.mainframes;


import org.apache.logging.log4j.Logger;

import com.mcneb10.mainframes.blocks.ModBlocks;
import com.mcneb10.mainframes.items.ModItems;
import com.mcneb10.mainframes.proxies.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CONSTS.MODID, canBeDeactivated = true, version = CONSTS.VERSION, acceptedMinecraftVersions = "1.10, 1.10.2", name = "Mainframes")
public class MainModClass {
	@SidedProxy(clientSide = "com.mcneb10.mainframes.proxies.ClientProxy", serverSide = "com.mcneb10.mainframes.proxies.ServerProxy")
	public static CommonProxy proxy;
	@Mod.Instance(CONSTS.MODID)
	public static MainModClass instance;
	public static MainframeTab tab;
	public static Logger logger;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = org.apache.logging.log4j.LogManager.getFormatterLogger(CONSTS.MODID);
		logger.info("PreInit");
		logger.info("Mainframe name: "+Utils.getInstance().generateNewMainframeName());
		tab = new MainframeTab(CreativeTabs.getNextID(), "mainframetab");
		ModItems.preInit();
		ModBlocks.preInit();
		proxy.preInit(event);
		proxy.registerTileEntities();
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Init");
		proxy.init(event);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("PostInits");
		proxy.postInit(event);
	}
}
