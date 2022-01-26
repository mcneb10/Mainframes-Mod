package com.mcneb10.mainframes.gui;

import com.mcneb10.mainframes.containers.ContainerSpool;
import com.mcneb10.mainframes.tileentities.TileEntitySpool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int SPOOL = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case SPOOL:
			return new ContainerSpool(player.inventory, (TileEntitySpool)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case SPOOL:
			return new GuiSpool(player.inventory, (TileEntitySpool)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

}
