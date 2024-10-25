package codyhuh.farmlife.client.model;

import codyhuh.farmlife.client.animations.DomesticTribullAnimation;
import codyhuh.farmlife.client.animations.GalliraptorAnimation;
import codyhuh.farmlife.common.entities.Galliraptor;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GalliraptorModel<T extends Galliraptor> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart tailtip;
	private final ModelPart head;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart rightleg;
	private final ModelPart rightfoot;
	private final ModelPart leftleg;
	private final ModelPart leftfoot;

	public GalliraptorModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.tailtip = this.tail.getChild("tailtip");
		this.head = this.body.getChild("head");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.rightleg = this.root.getChild("rightleg");
		this.rightfoot = this.rightleg.getChild("rightfoot");
		this.leftleg = this.root.getChild("leftleg");
		this.leftfoot = this.leftleg.getChild("leftfoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, -2.5F, -4.5F, 5.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 13).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 4.5F, 0.3491F, 0.0F, 0.0F));

		PartDefinition tailtip = tail.addOrReplaceChild("tailtip", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 1).addBox(-2.0F, -7.5F, -1.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 4).addBox(-0.5F, -9.5F, -3.5F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-1.0F, -7.5F, -3.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -4.5F, -2.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, -1).addBox(-0.5F, 1.0F, -3.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -1.0F, -1.0F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(6, -1).addBox(0.5F, 1.0F, -3.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -1.0F, -1.0F));

		PartDefinition rightleg = root.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.0F, 1.0F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 2.5F, 1.5F));

		PartDefinition rightfoot = rightleg.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(1, 18).mirror().addBox(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition leftleg = root.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 1.0F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.5F, 1.5F));

		PartDefinition leftfoot = leftleg.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(1, 18).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	public static LayerDefinition createChickBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightleg = root.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 23.0F, 0.5F));

		PartDefinition rightfoot = rightleg.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(-1, 3).addBox(-0.5F, 0.01F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.99F, 0.0F));

		PartDefinition leftleg = root.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 23.0F, 0.5F));

		PartDefinition leftfoot = leftleg.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(-1, 3).addBox(-0.5F, 0.01F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.99F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(7, 8).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 12).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tailtip = tail.addOrReplaceChild("tailtip", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.5F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T pEntity, float limbSwing, float limbSwingAmount, float pAgeInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.xRot = headPitch * 0.017453292F;
		this.head.yRot = netHeadYaw * 0.017453292F;

		if (this.young) this.animateWalk(GalliraptorAnimation.CHICK_WALK, limbSwing, limbSwingAmount, 2.0F, 100.0F);
		else this.animateWalk(GalliraptorAnimation.WALK, limbSwing, limbSwingAmount, 3.0F, 100.0F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}