package com.kilkags.touchecho.client.event;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.capability.DirtBallPower;
import com.kilkags.touchecho.item.ModItemList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class ClientEventHandler {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TouchEcho.MOD_ID + ":textures/gui/overlay.png");

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderGameOverlayPost(RenderGameOverlayEvent.Post event) {
        if(RenderGameOverlayEvent.ElementType.ALL.equals(event.getType())) {
            Minecraft mc = Minecraft.getMinecraft();
            Entity entity = mc.getRenderViewEntity();

            if(entity instanceof EntityPlayer) {
                ItemStack currentItem = ((EntityPlayer) entity).inventory.getCurrentItem();

                if(ModItemList.DIRT_BALL.equals(currentItem.getItem())) {
                    DirtBallPower power = entity.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
                    assert power != null;
                    float green = power.getGreenPower();
                    float orange = power.getOrangePower();
                    float blue = power.getBluePower();

                    ScaledResolution resolution = event.getResolution();
                    int width = resolution.getScaledWidth();
                    int height = resolution.getScaledHeight();

                    GlStateManager.enableBlend();
                    mc.getTextureManager().bindTexture(TEXTURE);
                    /*
                      由上到下：
                       主gui
                       橙左
                       绿左
                       蓝左
                       橙右
                       绿右
                       蓝右
                     */
                    mc.ingameGUI.drawTexturedModalRect(width / 2 - 175, height - 40, 0, 9, 80, 40);
                    mc.ingameGUI.drawTexturedModalRect(width / 2 - 170, height -35, orange < 4 ? 0 : 9, 0, 9, 9);
                    mc.ingameGUI.drawTexturedModalRect(width / 2 - 170, height -24, green < 4 ? 0 : 9, 0, 9, 9);
                    mc.ingameGUI.drawTexturedModalRect(width / 2 - 170, height -13, blue < 4 ? 0 : 9, 0, 9, 9);
                    mc.ingameGUI.drawString(mc.fontRenderer, "ORANGE" + orange, width / 2 - 158, height - 35, 0xFFFFFF);
                    mc.ingameGUI.drawString(mc.fontRenderer, "GREEN" + green, width / 2 - 158, height - 24, 0xFFFFFF);
                    mc.ingameGUI.drawString(mc.fontRenderer, "BLUE" + blue, width / 2 - 158, height - 13, 0xFFFFFF);
                }
            }
        }
    }
}
