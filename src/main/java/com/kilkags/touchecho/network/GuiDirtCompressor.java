package com.kilkags.touchecho.network;

import com.kilkags.touchecho.toolkits.GuiHelper;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import com.kilkags.touchecho.toolkits.Types;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author DustW
 */
@SideOnly(Side.CLIENT)
public class GuiDirtCompressor extends GuiContainer {
    public static final ResourceLocation TEXTURE = Types.Paths.getGuiTexturePath("dirt_compressor");

    public GuiDirtCompressor(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerDirtCompressor(player, world, x, y, z));
        this.xSize = 200;
        this.ySize = 200;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int tickNow = ((ContainerDirtCompressor) this.inventorySlots).getCompressorProgress();
        int left = (this.width - this.xSize) /2;
        int top = (this.height - this.ySize) /2;

        int[] inventoryUpperLeftPos = GuiHelper.standardInventoryUpperLeftPos(this);

        GuiHelper.drawBackground(this, this.xSize, this.ySize);
        GuiHelper.drawInventory(this, inventoryUpperLeftPos[0], inventoryUpperLeftPos[1]);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);

        int barHeightCross = 18;
        int barWidthCross = 1 + LotusSymphony.getTexturesMovePreTick(84, 240, tickNow);
        this.drawTexturedModalRect(left + 44, top + 47, 0, 166, barWidthCross, barHeightCross);

        int barHeightErect = 1 + LotusSymphony.getTexturesMovePreTick(17, 240 / 20, tickNow % 20);
        int barWidthErect = 17;
        this.drawTexturedModalRect(left + 82, top + 36, 87, 166, barWidthErect, barHeightErect);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = I18n.format("tile.touchecho.dirtCompressor.name");
        this.drawCenteredString(this.fontRenderer, text, this.xSize / 2, 6, 0x00404040);
    }
}
