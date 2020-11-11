package com.kilkags.touchecho.toolkits;

import com.kilkags.touchecho.TouchEcho;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class Types {
    public static class DamageSources {
        public static final String IN_FIRE ="inFire";
        public static final String LIGHTNING_BOLT = "lightningBolt";
        public static final String ON_FIRE = "onFire";
        public static final String LAVA = "lava";
        public static final String HOT_FLOOR = "hotFloor";
        public static final String IN_WALL = "inWall";
        public static final String CRAMMING = "cramming";
        public static final String DROWN = "drown";
        public static final String STARVE = "starve";
        public static final String CACTUS = "cactus";
        public static final String FALL = "fall";
        public static final String FLY_INTO_WALL = "flyIntoWall";
        public static final String OUT_OF_WORLD = "outOfWorld";
        public static final String GENERIC = "generic";
        public static final String MAGIC = "magic";
        public static final String WITHER = "wither";
        public static final String ANVIL = "anvil";
        public static final String FALLING_BLOCK = "fallingBlock";
        public static final String DRAGON_BREATH = "dragonBreath";
        public static final String FIREWORKS = "fireworks";
    }

    public static class Color {
        public static final int PURE_WHITE = 0xFFFFFF;
        public static final int PURE_BLACK = 0x000000;
        public static final int PURE_RED = 0xFF0000;
        public static final int PURE_BLUE = 0x0000FF;
        public static final int PURE_GREEN = 0x00FF00;

    }

    public static class Tools {
        public static final String SHOVEL = "shovel";
        public static final String AXE = "axe";
        public static final String PICKAXE = "pickaxe";
    }

    public static class JsonTypes {
        public static final String HANDHELD = "handheld";
        public static final String GENERATED = "generated";
    }

    public static class Paths {
        private static final String BLOCK_STATES_FOLDER_PATH = "../src/main/resources/assets/touchecho/blockstates";
        private static final String MODELS_PATH = "../src/main/resources/assets/touchecho/models";

        public static String getBlockStatesFolderPath() {
            return BLOCK_STATES_FOLDER_PATH;
        }
        public static String getModelsPath() {
            return MODELS_PATH;
        }

        public static ResourceLocation getEntityTexturePath(String entityID) {
            return new ResourceLocation(TouchEcho.MOD_ID + ":textures/entity/" + entityID + "/" + entityID + ".png");
        }

        public static ResourceLocation getPotionTexturePath(String textureName) {
            return new ResourceLocation(TouchEcho.MOD_ID + ":textures/gui/" + textureName + ".png");
        }

        public static ResourceLocation getGuiTexturePath(String textureName) {
            return new ResourceLocation(TouchEcho.MOD_ID + ":textures/gui/container/" + textureName + ".png");
        }

        public static ResourceLocation[] getFluidTexturePath(String fluidName) {
            return  new ResourceLocation[] {
                    new ResourceLocation(TouchEcho.MOD_ID + ":fluid/"+ fluidName +"_still"),
                    new ResourceLocation(TouchEcho.MOD_ID + ":fluid/"+ fluidName +"_flow")};
        }
    }
}
