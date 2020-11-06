package com.kilkags.touchecho.client.entity.slimeking;// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author DustW
 */
public class ModelSlimeKing extends ModelBase {
	private final ModelRenderer bone;

	public ModelSlimeKing() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 20, 24, 2.0F, -10.0F, -7.0F, 5, 5, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 24, -7.0F, -10.0F, -7.0F, 5, 5, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F,  -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 0.0F,   -4.0F, -7.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}