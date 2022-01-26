package com.mcneb10.mainframes.proxies;

import com.mcneb10.mainframes.CONSTS;
import com.mcneb10.mainframes.tileentities.TileEntityDiskDrive;
import com.mcneb10.mainframes.tileentities.TileEntitySpool;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	public void init(FMLInitializationEvent event) {
		
	}
	public void postInit(FMLPostInitializationEvent event) {
	
	}
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySpool.class, CONSTS.MODID+":spool");
		GameRegistry.registerTileEntity(TileEntityDiskDrive.class, CONSTS.MODID+":diskdrive");
	}
}
