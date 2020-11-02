package com.kilkags.touchecho.potion;

import com.google.common.base.CaseFormat;
import com.kilkags.touchecho.TouchEcho;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class PotionDirtProtection extends Potion {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TouchEcho.MOD_ID + ":textures/gui/potion.png");

    protected PotionDirtProtection() {
        super(false, 0x806144);
        this.setRegistryName(TouchEcho.MOD_ID + ":dirt_protection");
        this.setPotionName("effect." + TouchEcho.MOD_ID + ".dirtProtection");
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        int duration = effect.getDuration();
        int fIndex = (duration % 16) / 2;
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, fIndex * 18, 0, 18, 18);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        int duration = effect.getDuration();
        int fIndex = (duration % 16) / 2;
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.ingameGUI.drawTexturedModalRect(x + 3, y + 3, fIndex * 18, 0, 18, 18);
    }
}
