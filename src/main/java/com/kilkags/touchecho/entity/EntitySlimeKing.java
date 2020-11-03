package com.kilkags.touchecho.entity;

import com.kilkags.touchecho.client.entity.slimeking.RenderSlimeKing;
import com.kilkags.touchecho.lotus.LotusSymphony;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntitySlimeKing extends EntityMob {
    public static final String ID = "slime_king";
    public static final String NAME = LotusSymphony.getLangKeyFromRegKey(ID);
    public EntitySlimeKing(World worldIn) {
        super(worldIn);
        this.setSize(3F, 3F);

        ModEntityList.ENTITY_MAP.put(this, RenderSlimeKing::new);
    }
}
