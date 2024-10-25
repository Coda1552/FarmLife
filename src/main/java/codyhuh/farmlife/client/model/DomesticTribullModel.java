package codyhuh.farmlife.client.model;

import codyhuh.farmlife.client.animations.DomesticTribullAnimation;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DomesticTribullModel<T extends Entity> extends AgeableHierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart dewlap;
	private final ModelPart hornLeft;
	private final ModelPart hornLeft2;
	private final ModelPart hornRight;
	private final ModelPart hornRight2;
	private final ModelPart armLeft;
	private final ModelPart armRight;
	private final ModelPart legBack;

	public DomesticTribullModel(ModelPart root) {
        super(0.5F, 24.0F);
        this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = this.body.getChild("head");
		this.dewlap = this.head.getChild("dewlap");
		this.hornLeft = this.head.getChild("hornLeft");
		this.hornLeft2 = this.hornLeft.getChild("hornLeft2");
		this.hornRight = this.head.getChild("hornRight");
		this.hornRight2 = this.hornRight.getChild("hornRight2");
		this.armLeft = this.root.getChild("armLeft");
		this.armRight = this.root.getChild("armRight");
		this.legBack = this.root.getChild("legBack");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, -6.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 26).addBox(-4.5F, -4.0F, 0.0F, 9.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.5F, -6.0F, -14.0F, 13.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 12.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 48).addBox(-3.5F, -3.0F, -7.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -14.0F));

		PartDefinition dewlap = head.addOrReplaceChild("dewlap", CubeListBuilder.create().texOffs(21, 44).addBox(0.0F, -4.0F, -4.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -3.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition hornLeft = head.addOrReplaceChild("hornLeft", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.0F, -1.5F, 0.2618F, 0.0F, 1.0472F));

		PartDefinition hornLeft2 = hornLeft.addOrReplaceChild("hornLeft2", CubeListBuilder.create().texOffs(30, 26).addBox(-0.5F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -1.0F));

		PartDefinition hornRight = head.addOrReplaceChild("hornRight", CubeListBuilder.create().texOffs(0, 26).addBox(-1.5F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -3.0F, -1.5F, 0.2618F, 0.0F, -1.0472F));

		PartDefinition hornRight2 = hornRight.addOrReplaceChild("hornRight2", CubeListBuilder.create().texOffs(30, 26).addBox(-1.5F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -1.0F));

		PartDefinition armLeft = root.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(41, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 6.0F, -2.0F));

		PartDefinition armRight = root.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(41, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 6.0F, -2.0F));

		PartDefinition legBack = root.addOrReplaceChild("legBack", CubeListBuilder.create().texOffs(41, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 15.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.xRot = headPitch * 0.017453292F;
		this.head.yRot = netHeadYaw * 0.017453292F;

		this.animateWalk(DomesticTribullAnimation.WALK, limbSwing, limbSwingAmount, 3.0F, 100.0F);
		if (this.young) this.applyStatic(DomesticTribullAnimation.BABY_TRANSFORM);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}