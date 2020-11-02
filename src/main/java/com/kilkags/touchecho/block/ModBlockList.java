package com.kilkags.touchecho.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.HashMap;
import java.util.Map;

public class ModBlockList {
    public static final Map<Block, Object[]> BLOCK_MAP = new HashMap<>();

    public static final BlockCommon BLOCK_COMPRESSED_DIRT =
            new BlockCommon(Material.GRASS, "compressed_dirt", "shovel", 1, 0.5F);
}
