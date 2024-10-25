package codyhuh.farmlife.client.model;

import codyhuh.farmlife.common.entities.item.SeaPlumFruitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SeaPlumModel<T extends SeaPlumFruitEntity> extends EntityModel<T> {
	private final ModelPart stem;
	private final ModelPart fruit;

	public SeaPlumModel(ModelPart root) {
		this.stem = root.getChild("stem");
		this.fruit = this.stem.getChild("fruit");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stem = partdefinition.addOrReplaceChild("stem", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -32.0F, 0.0F, 4.0F, 32.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, -4).addBox(0.0F, -32.0F, -2.0F, 0.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fruit = stem.addOrReplaceChild("fruit", CubeListBuilder.create().texOffs(10, 22).addBox(-2.5F, -5.0F, -2.25F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(11, 17).addBox(-1.5F, -9.0F, 0.25F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(11, 14).addBox(0.0F, -9.0F, -1.25F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -27.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		stem.getAllParts().forEach(ModelPart::resetPose);

		stem.xRot = Mth.sin(ageInTicks * 0.15F) * 0.3F;
		stem.zRot = Mth.cos(ageInTicks * 0.15F) * 0.3F;

		fruit.xRot = -Mth.sin(ageInTicks * 0.15F) * 0.15F;
		fruit.zRot = -Mth.cos(ageInTicks * 0.15F) * 0.15F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}