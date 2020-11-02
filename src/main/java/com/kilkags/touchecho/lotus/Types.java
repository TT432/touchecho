package com.kilkags.touchecho.lotus;

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

    public static class Tools {
        public static final String SHOVEL = "shovel";
        public static final String AXE = "axe";
        public static final String PICKAXE = "pickaxe";
    }

    public static class JsonTypes {
        public static final String TOOL = "tool";
        public static final String ITEM = "item";
    }

    public static class Paths {
        private static final String BLOCK_STATES_FOLDER_PATH = "../src/main/resources/assets/touchecho/blockstates";


        public static String getBlockStatesFolderPath() {
            return BLOCK_STATES_FOLDER_PATH;
        }
    }
}
