package com.kilkags.touchecho.entity;

import com.kilkags.touchecho.toolkits.LotusSymphony;
import com.kilkags.touchecho.toolkits.Types;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class ModEntityList {
    public static final Map<EntityEntry, Object[]> ENTITY_MAP = new HashMap<>();
    public static int id = 0;
    public static void initEntityMap() {
        ModEntityList.ENTITY_MAP.put(LotusSymphony.getEntryFromEntity(EntitySlimeKing.class, EntitySlimeKing.ENTITY_SLIME_KING,id++, Types.Color.PURE_WHITE, Types.Color.PURE_BLACK), new Object[]{EntitySlimeKing.ENTITY_SLIME_KING});
        ModEntityList.ENTITY_MAP.put(LotusSymphony.getEntryFromEntity(EntityDirtBall.class, EntityDirtBall.ENTITY_DIRT_BALL,id++, true), new Object[]{EntityDirtBall.ENTITY_DIRT_BALL});
    }
}
