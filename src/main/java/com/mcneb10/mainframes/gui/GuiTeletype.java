package com.mcneb10.mainframes.gui;

import com.mcneb10.mainframes.CONSTS;
import com.mcneb10.mainframes.containers.ContainerTeletype;
import com.mcneb10.mainframes.tileentities.TileEntityTeletype;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTeletype extends GuiContainer {

	//private TileEntityDiskDrive te;
	private IInventory playerInv;
	public GuiTeletype(IInventory playerInv, TileEntityTeletype te) {
		super(new ContainerTeletype(playerInv, te));
		this.xSize=176;
		this.ySize=166;
		//this.te = te;
		this.playerInv = playerInv;
	}
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(CONSTS.MODID, "textures/gui/container/teletype.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format("container.teletype"); 
		this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752);
	}
}