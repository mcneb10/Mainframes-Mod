package com.mcneb10.mainframes.gui;

import com.mcneb10.mainframes.CONSTS;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiManual extends GuiScreen {
	int xSize = 175;
    int ySize = 180;
    int guiLeft;
    int guiTop;
	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
	}
	public GuiManual() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1,1,1,1);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(CONSTS.MODID, "textures/gui/book/book.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 20, 1, this.xSize, this.ySize);
		this.mc.fontRendererObj.drawSplitString(I18n.format("book.manual.page1"), this.guiLeft+16, this.guiTop+16, 115, 4210752);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
