package codyhuh.farmlife.client.model;

import codyhuh.farmlife.client.animations.PlatefishAnimation;
import codyhuh.farmlife.common.entities.Platefish;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class PlatefishModel<T extends Platefish> extends AgeableHierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart tailBase;
	private final ModelPart tail;
	private final ModelPart fluke;
	private final ModelPart mouthplate;
	private final ModelPart leftplate;
	private final ModelPart rightplate;
	private final ModelPart leftfin;
	private final ModelPart rightfin;

	public PlatefishModel(ModelPart root) {
		super(0.5F, 24.0F);
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.tailBase = this.body.getChild("tailBase");
		this.tail = this.tailBase.getChild("tail");
		this.fluke = this.tail.getChild("fluke");
		this.mouthplate = this.body.getChild("mouthplate");
		this.leftplate = this.body.getChild("leftplate");
		this.rightplate = this.body.getChild("rightplate");
		this.leftfin = this.body.getChild("leftfin");
		this.rightfin = this.body.getChild("rightfin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -4.0F));

		PartDefinition tailBase = body.addOrReplaceChild("tailBase", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 6.0F));

		PartDefinition tail = tailBase.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(28, 10).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 6.0F));

		PartDefinition fluke = tail.addOrReplaceChild("fluke", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -3.0F, -1.5F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.5F));

		PartDefinition mouthplate = body.addOrReplaceChild("mouthplate", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -6.0F));

		PartDefinition leftplate = body.addOrReplaceChild("leftplate", CubeListBuilder.create().texOffs(44, 0).addBox(-1.0F, -2.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition rightplate = body.addOrReplaceChild("rightplate", CubeListBuilder.create().texOffs(44, 0).mirror().addBox(0.0F, -2.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition leftfin = body.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(0, 18).addBox(-8.0F, -0.5F, -0.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.5F, 4.5F));

		PartDefinition rightfin = body.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(0.0F, -0.5F, -0.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -0.5F, 4.5F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWater()) {
			this.animateWalk(PlatefishAnimation.SWIM, limbSwing, limbSwingAmount, 3.0F, 100.0F);
		}
	}

	@Override
	public ModelPart root() {
		return root;
	}
}