package com.kilkags.touchecho.client.entity.slimeking;

import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.toolkits.Types;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 */
public class RenderSlimeKing extends RenderLiving<EntityMob> {
    public RenderSlimeKing(RenderManager manager) {
        super(manager, new ModelSlimeKing(), 2F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMob entity) {
        return Types.Paths.getEntityTexturePath(EntitySlimeKing.ENTITY_SLIME_KING);
    }
}
