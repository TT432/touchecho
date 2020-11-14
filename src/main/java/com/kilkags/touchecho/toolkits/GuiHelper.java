package com.kilkags.touchecho.toolkits;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class GuiHelper {
    private static final ResourceLocation TEXTURE = Types.Paths.getGuiTexturePath("gui");
    /** upper left x [0] upper left y [1] textures width [2] texture height [3] */
    private static final int[] INVENTORY_TEXTURE_POS = new int[] {0, 47, 162, 76};
    private static final int[] BACKGROUND_TEXTURE_POS = new int[] {27, 19, 27, 27};
    private static final int[] UPPER_LEFT_TEXTURE_POS = new int[] {0, 0, 5, 5};
    private static final int[] UPPER_RIGHT_TEXTURE_POS = new int[] {8, 0, 5, 5};
    private static final int[] BOTTOM_LEFT_TEXTURE_POS = new int[] {0, 8, 5, 5};
    private static final int[] BOTTOM_RIGHT_TEXTURE_POS = new int[] {8, 8, 5, 5};
    private static final int[] TOP_SIDE_TEXTURE_POS = new int[] {6, 0, 1, 5};
    private static final int[] LEFT_SIDE_TEXTURE_POS = new int[] {0, 6, 5, 1};
    private static final int[] RIGHT_SIDE_TEXTURE_POS = new int[] {8, 6, 5, 1};
    private static final int[] BOTTOM_SIDE_TEXTURE_POS = new int[] {6, 8, 1, 5};

    public static List<Slot> addInventory(InventoryPlayer inventoryPlayer, int firstLineX, int firstLineY) {
        List<Slot> slots = new ArrayList<>();

        int secondLineY = 18 + firstLineY;
        int thirdLineY = 18 + secondLineY;
        int handLineY = 22 + thirdLineY;

        int[] range = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for(int i : range) {
            slots.add(new Slot(inventoryPlayer, i + 9, firstLineX + 18 * i, firstLineY));
            slots.add(new Slot(inventoryPlayer, i + 18, firstLineX + 18 * i, secondLineY));
            slots.add(new Slot(inventoryPlayer, i + 27, firstLineX + 18 * i, thirdLineY));
            slots.add(new Slot(inventoryPlayer, i, firstLineX + 18 * i, handLineY));
        }

        return slots;
    }

    public static void drawInventory(GuiContainer guiContainer,int upperLeftX, int upperLeftY) {
        int left = (guiContainer.width - guiContainer.getXSize()) /2;
        int top = (guiContainer.height - guiContainer.getYSize()) /2;

        guiContainer.mc.getTextureManager().bindTexture(TEXTURE);

        int x = left + upperLeftX;
        int y = top + upperLeftY;
        guiContainer.drawTexturedModalRect(x, y, INVENTORY_TEXTURE_POS[0], INVENTORY_TEXTURE_POS[1], INVENTORY_TEXTURE_POS[2], INVENTORY_TEXTURE_POS[3]);
    }

    public static void drawBackground(GuiContainer guiContainer, int width, int height) {
        int left = (guiContainer.width - guiContainer.getXSize()) /2;
        int top = (guiContainer.height - guiContainer.getYSize()) /2;

        guiContainer.mc.getTextureManager().bindTexture(TEXTURE);

        int bottomLeftY = top + (height - BOTTOM_LEFT_TEXTURE_POS[3]);
        int upperRightX = left + (width - UPPER_RIGHT_TEXTURE_POS[2]);
        int bottomRightX = left + (width - BOTTOM_RIGHT_TEXTURE_POS[2]);
        int bottomRightY = top + (height - BOTTOM_RIGHT_TEXTURE_POS[3]);

        guiContainer.drawTexturedModalRect(left, top, UPPER_LEFT_TEXTURE_POS[0], UPPER_LEFT_TEXTURE_POS[1], UPPER_LEFT_TEXTURE_POS[2], UPPER_LEFT_TEXTURE_POS[3]);
        guiContainer.drawTexturedModalRect(left, bottomLeftY, BOTTOM_LEFT_TEXTURE_POS[0], BOTTOM_LEFT_TEXTURE_POS[1], BOTTOM_LEFT_TEXTURE_POS[2], BOTTOM_LEFT_TEXTURE_POS[3]);
        guiContainer.drawTexturedModalRect(upperRightX, top, UPPER_RIGHT_TEXTURE_POS[0], UPPER_RIGHT_TEXTURE_POS[1], UPPER_RIGHT_TEXTURE_POS[2], UPPER_RIGHT_TEXTURE_POS[3]);
        guiContainer.drawTexturedModalRect(bottomRightX, bottomRightY, BOTTOM_RIGHT_TEXTURE_POS[0], BOTTOM_RIGHT_TEXTURE_POS[1], BOTTOM_RIGHT_TEXTURE_POS[2], BOTTOM_RIGHT_TEXTURE_POS[3]);

        for (int i = 0; i < width - UPPER_LEFT_TEXTURE_POS[2] - UPPER_RIGHT_TEXTURE_POS[2]; i ++) {
            guiContainer.drawTexturedModalRect(left + UPPER_RIGHT_TEXTURE_POS[2] + i, top, TOP_SIDE_TEXTURE_POS[0], TOP_SIDE_TEXTURE_POS[1], TOP_SIDE_TEXTURE_POS[2], TOP_SIDE_TEXTURE_POS[3]);
        }
        for (int i = 0; i < height - UPPER_RIGHT_TEXTURE_POS[3] - BOTTOM_RIGHT_TEXTURE_POS[3]; i ++) {
            guiContainer.drawTexturedModalRect(upperRightX, top + UPPER_RIGHT_TEXTURE_POS[3] + i, RIGHT_SIDE_TEXTURE_POS[0], RIGHT_SIDE_TEXTURE_POS[1], RIGHT_SIDE_TEXTURE_POS[2], RIGHT_SIDE_TEXTURE_POS[3]);
        }
        for (int i = 0; i < height - UPPER_LEFT_TEXTURE_POS[3] - BOTTOM_LEFT_TEXTURE_POS[3]; i ++) {
            guiContainer.drawTexturedModalRect(left, top + UPPER_LEFT_TEXTURE_POS[2] + i, LEFT_SIDE_TEXTURE_POS[0], LEFT_SIDE_TEXTURE_POS[1], LEFT_SIDE_TEXTURE_POS[2], LEFT_SIDE_TEXTURE_POS[3]);
        }
        for (int i = 0; i < width - BOTTOM_RIGHT_TEXTURE_POS[2] - BOTTOM_RIGHT_TEXTURE_POS[2]; i ++) {
            guiContainer.drawTexturedModalRect(left + BOTTOM_LEFT_TEXTURE_POS[2] + i, bottomLeftY, BOTTOM_SIDE_TEXTURE_POS[0], BOTTOM_SIDE_TEXTURE_POS[1], BOTTOM_SIDE_TEXTURE_POS[2], BOTTOM_SIDE_TEXTURE_POS[3]);
        }

        drawBackgroundHeight(guiContainer, top + 5, left + 5, width - 10, height - 10);
    }

    private static void drawBackgroundHeight(GuiContainer guiContainer, int top, int left, int width, int height) {
        int heightDrawCount = height / BACKGROUND_TEXTURE_POS[3];
        int lastBackgroundLineHeight = height % BACKGROUND_TEXTURE_POS[3];
        int count = 0;
        for (int i = 0; i < heightDrawCount; i++) {
            drawBackgroundWidth(guiContainer, top + i * BACKGROUND_TEXTURE_POS[3], left, width, BACKGROUND_TEXTURE_POS[3]);
            count = i + 1;
        }
        drawBackgroundWidth(guiContainer, top + count * BACKGROUND_TEXTURE_POS[3], left, width, lastBackgroundLineHeight);
    }

    private static void drawBackgroundWidth(GuiContainer guiContainer, int top, int left, int width, int height) {
        int widthDrawCount = width / BACKGROUND_TEXTURE_POS[2];
        int lastBackgroundBlockWidth = width % BACKGROUND_TEXTURE_POS[2];
        int count = 0;
        for (int i = 0; i < widthDrawCount; i++) {
            guiContainer.drawTexturedModalRect(left + i * BACKGROUND_TEXTURE_POS[2], top, BACKGROUND_TEXTURE_POS[0], BACKGROUND_TEXTURE_POS[1], BACKGROUND_TEXTURE_POS[2], height);
            count = i + 1;
        }
        guiContainer.drawTexturedModalRect(left + count * BACKGROUND_TEXTURE_POS[2], top, BACKGROUND_TEXTURE_POS[0], BACKGROUND_TEXTURE_POS[1], lastBackgroundBlockWidth, height);
    }

    public static int[] standardInventoryUpperLeftPos(GuiContainer guiContainer) {
        int x = (guiContainer.getXSize() - INVENTORY_TEXTURE_POS[2]) / 2;
        int y = guiContainer.getYSize() - x - INVENTORY_TEXTURE_POS[3];
        return new int[] {x, y};
    }
}
