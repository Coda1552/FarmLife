package teamdraco.wcfarmlife.client.model;

import com.google.common.collect.ImmutableList;
import teamdraco.wcfarmlife.common.entities.GalliraptorEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class GalliraptorModel<T extends Entity> extends SegmentedModel<GalliraptorEntity> {
    public ModelRenderer body;
    public ModelRenderer tailbase;
    public ModelRenderer head;
    public ModelRenderer leftwing;
    public ModelRenderer rightwing;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer tailbasefeathers;
    public ModelRenderer tail;
    public ModelRenderer tailfeathers;
    public ModelRenderer crest;
    public ModelRenderer beak;
    public ModelRenderer crest_baby;
    public ModelRenderer head_baby;
    public ModelRenderer rightleg_baby;
    public ModelRenderer leftleg_baby;
    public ModelRenderer beak_baby;
    public ModelRenderer tail_baby;
    public ModelRenderer body_baby;

    @Override
    public Iterable<ModelRenderer> parts() {
        if (this.young) {
            return ImmutableList.of(body_baby);
        } else {
            return ImmutableList.of(body);
        }
    }

    public GalliraptorModel() {
        setAngles();
    }

    protected abstract void setAngles();

    public static class Adult extends GalliraptorModel{
        @Override
        protected void setAngles() {
            this.texWidth = 64;
            this.texHeight = 32;
            this.tailbasefeathers = new ModelRenderer(this, 24, 10);
            this.tailbasefeathers.setPos(0.0F, 0.0F, 0.0F);
            this.tailbasefeathers.addBox(-3.0F, -1.0F, 0.0F, 6.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.rightwing = new ModelRenderer(this, 44, 0);
            this.rightwing.setPos(-2.5F, -1.0F, -2.5F);
            this.rightwing.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.head = new ModelRenderer(this, 0, 12);
            this.head.setPos(0.0F, 0.0F, -3.0F);
            this.head.addBox(-1.5F, -6.0F, -2.5F, 3.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.leftwing = new ModelRenderer(this, 44, 0);
            this.leftwing.mirror = true;
            this.leftwing.setPos(2.5F, -1.0F, -2.5F);
            this.leftwing.addBox(0.0F, 0.0F, -1.0F, 1.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.beak = new ModelRenderer(this, 11, 25);
            this.beak.setPos(0.0F, -5.5F, -2.5F);
            this.beak.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.tail = new ModelRenderer(this, 24, 0);
            this.tail.setPos(0.0F, 0.0F, 8.0F);
            this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(tail, -0.17453292519943295F, 0.0F, 0.0F);
            this.tailfeathers = new ModelRenderer(this, 22, 21);
            this.tailfeathers.setPos(0.0F, 0.0F, 0.0F);
            this.tailfeathers.addBox(-3.5F, -1.0F, -0.5F, 7.0F, 1.0F, 10.0F, 0.0F, 0.0F, 0.0F);
            this.tailbase = new ModelRenderer(this, 12, 12);
            this.tailbase.setPos(0.0F, -1.5F, 3.0F);
            this.tailbase.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(tailbase, 0.3490658503988659F, 0.0F, 0.0F);
            this.leftleg = new ModelRenderer(this, 51, 10);
            this.leftleg.mirror = true;
            this.leftleg.setPos(1.5F, 2.5F, 2.5F);
            this.leftleg.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.rightleg = new ModelRenderer(this, 51, 10);
            this.rightleg.setPos(-1.5F, 2.5F, 2.5F);
            this.rightleg.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.crest = new ModelRenderer(this, 0, 22);
            this.crest.setPos(0.0F, -6.0F, -0.5F);
            this.crest.addBox(-0.5F, -2.0F, -3.0F, 1.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.body = new ModelRenderer(this, 0, 0);
            this.body.setPos(0.0F, 16.5F, 0.0F);
            this.body.addBox(-2.5F, -2.5F, -3.5F, 5.0F, 5.0F, 7.0F, 0.0F, 0.0F, 0.0F);
            this.tailbase.addChild(this.tailbasefeathers);
            this.body.addChild(this.rightwing);
            this.body.addChild(this.head);
            this.body.addChild(this.leftwing);
            this.head.addChild(this.beak);
            this.tailbase.addChild(this.tail);
            this.tail.addChild(this.tailfeathers);
            this.body.addChild(this.tailbase);
            this.body.addChild(this.leftleg);
            this.body.addChild(this.rightleg);
            this.head.addChild(this.crest);
        }
    }

    public static class Child extends GalliraptorModel {
        @Override
        protected void setAngles() {
            this.texWidth = 64;
            this.texHeight = 32;
            this.crest_baby = new ModelRenderer(this, 0, 15);
            this.crest_baby.setPos(0.0F, -2.0F, -1.5F);
            this.crest_baby.addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.head_baby = new ModelRenderer(this, 0, 7);
            this.head_baby.setPos(0.0F, -0.5F, -1.0F);
            this.head_baby.addBox(-1.0F, -2.5F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.rightleg_baby = new ModelRenderer(this, 8, 7);
            this.rightleg_baby.setPos(-1.0F, 1.5F, 0.0F);
            this.rightleg_baby.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.leftleg_baby = new ModelRenderer(this, 8, 7);
            this.leftleg_baby.mirror = true;
            this.leftleg_baby.setPos(1.0F, 1.5F, 0.0F);
            this.leftleg_baby.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.beak_baby = new ModelRenderer(this, 0, 12);
            this.beak_baby.setPos(0.0F, -2.0F, -1.5F);
            this.beak_baby.addBox(-1.0F, -0.5F, -1.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.tail_baby = new ModelRenderer(this, 14, 0);
            this.tail_baby.setPos(0.0F, -1.0F, 2.0F);
            this.tail_baby.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.body_baby = new ModelRenderer(this, 0, 0);
            this.body_baby.setPos(0.0F, 20.5F, 0.0F);
            this.body_baby.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.head_baby.addChild(this.crest_baby);
            this.body_baby.addChild(this.head_baby);
            this.body_baby.addChild(this.rightleg_baby);
            this.body_baby.addChild(this.leftleg_baby);
            this.head_baby.addChild(this.beak_baby);
            this.body_baby.addChild(this.tail_baby);
        }
    }

    @Override
    public void setupAnim(GalliraptorEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        if (this.young) {
            this.head_baby.xRot = headPitch * ((float)Math.PI / 180F);
            this.head_baby.yRot = netHeadYaw * ((float)Math.PI / 180F);
            this.head_baby.xRot = MathHelper.cos(2.0F + f * speed * 0.4F) * degree * 0.2F * f1 - 0.1F;
            this.rightleg_baby.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.0F * f1;
            this.leftleg_baby.xRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.0F * f1;
            this.tail_baby.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.3F * f1 - 0.2F;
        } else {
            this.head.xRot = headPitch * ((float)Math.PI / 180F);
            this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
            this.head.xRot = MathHelper.cos(2.0F + f * speed * 0.4F) * degree * 0.2F * f1 - 0.1F;
            this.rightleg.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.0F * f1;
            this.leftleg.xRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.0F * f1;
            this.tailbase.xRot = MathHelper.cos(2.0F + f * speed * 0.4F) * degree * 0.5F * f1 + 0.35F;
            this.tail.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.3F * f1 - 0.2F;
            this.tailbase.yRot = MathHelper.cos(f * speed * 0.2F) * degree * 0.4F * f1;
            this.leftwing.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.3F * f1 + 0.3F;
            this.leftwing.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.4F * f1 - 0.1F;
            this.rightwing.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1 + 0.1F;
            this.rightwing.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.3F * f1 + 0.3F;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
