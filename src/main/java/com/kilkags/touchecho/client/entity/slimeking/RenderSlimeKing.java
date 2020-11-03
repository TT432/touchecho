package com.kilkags.touchecho.client.renderer;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.client.ModelSlimeKing;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.lotus.Types;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderSlimeKing extends RenderLiving {
    public RenderSlimeKing(RenderManager manager) {
        super(manager, new ModelSlimeKing(3), 2F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return Types.Paths.getEntityTexturePath(EntitySlimeKing.ID);
    }

}
