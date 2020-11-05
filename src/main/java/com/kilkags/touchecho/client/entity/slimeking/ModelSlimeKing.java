package com.kilkags.touchecho.client.entity.slimeking;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * @author DustW
 */
public class ModelSlimeKing extends ModelBase {
    private final ModelRenderer rightEye;
    private final ModelRenderer liftEye;
    private final ModelRenderer body;
    private final ModelRenderer mouth;

    public ModelSlimeKing() {
        textureWidth = 64;
        textureHeight = 64;
        int size = 5;

        rightEye = new ModelRenderer(this);
        rightEye.setRotationPoint(7.0F, 18.0F, -7.0F);
        rightEye.cubeList.add(new ModelBox(rightEye, 0, 24, -14.0F, -4.0F, 0.0F, 5, 5, 5, 0.0F, false));

        liftEye = new ModelRenderer(this);
        liftEye.setRotationPoint(6.0F, 24.0F, -6.0F);
        liftEye.cubeList.add(new ModelBox(liftEye, 20, 24, -4.0F, -10.0F, -1.0F, 5, 5, 5, 0.0F, false));

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));

        mouth = new ModelRenderer(this);
        mouth.setRotationPoint(0.0F, 24.0F, 0.0F);
        mouth.cubeList.add(new ModelBox(mouth, 0, 0, 1.0F, -3.0F, -7.0F, 2, 2, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        rightEye.render(f5);
        liftEye.render(f5);
        body.render(f5);
        mouth.render(f5);
    }
}
