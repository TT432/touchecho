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

		float size = 16F;

		int sizeBo = 12;
		int sizeLe = 5;
		int sizeMo = 2;

		float ratioEtoB = (float) sizeLe / sizeBo;
		float ratioMtoB = (float) sizeMo / sizeBo;

		float deltaLe = size * ratioEtoB;
		float deltaMo = size * ratioMtoB;

		float xPosBo = -6.0F;
		float xPosLe = 2 + 0.75F * size;
		float xPosRe = -7 - 0.75F * size;
		float xPosMo = -size / 6;

		float yPosBo = -12.0F - size;
		float yPosLe = -10 - 1.25F * size;
		float yPosMo = -4 - size / 2;

		float zPosBo = -6.0F;
		float zPosLe = -7 - 0.75F * size;
		float zPosMo = -7 - 5 * size / 6;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0,  0,  xPosBo, yPosBo, zPosBo, sizeBo, sizeBo, sizeBo, size, false));
		bone.cubeList.add(new ModelBox(bone, 20, 24, xPosLe, yPosLe, zPosLe, sizeLe, sizeLe, sizeLe, deltaLe, false));
		bone.cubeList.add(new ModelBox(bone, 0,  24, xPosRe, yPosLe, zPosLe, sizeLe, sizeLe, sizeLe, deltaLe, false));
		bone.cubeList.add(new ModelBox(bone, 0,  0,  xPosMo, yPosMo, zPosMo, sizeMo, sizeMo, sizeMo, deltaMo, false));
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