package teamdraco.wcfarmlife.client.model;

import com.google.common.collect.ImmutableList;
import teamdraco.wcfarmlife.common.entities.DomesticTribullEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class DomesticTribullModel<T extends Entity> extends AgeableModel<DomesticTribullEntity> {
    public ModelRenderer body;
    public ModelRenderer hips;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer legBack;
    public ModelRenderer dewlap;
    public ModelRenderer hornLeft;
    public ModelRenderer hornRight;
    public ModelRenderer hornLeft2;
    public ModelRenderer hornRight2;

    public DomesticTribullModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.head = new ModelRenderer(this, 0, 48);
        this.head.setPos(0.0F, 1.0F, -7.0F);
        this.head.addBox(-3.5F, -3.0F, -7.0F, 7.0F, 6.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 8.0F, -6.0F);
        this.body.addBox(-6.0F, -6.0F, -7.0F, 12.0F, 12.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.hips = new ModelRenderer(this, 0, 26);
        this.hips.setPos(0.0F, 0.0F, 7.0F);
        this.hips.addBox(-4.5F, -4.0F, 0.0F, 9.0F, 10.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.dewlap = new ModelRenderer(this, 21, 44);
        this.dewlap.setPos(0.0F, 3.0F, -7.0F);
        this.dewlap.addBox(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(dewlap, 1.2217304763960306F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 41, 0);
        this.armLeft.setPos(3.5F, 6.0F, -2.0F);
        this.armLeft.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setPos(0.0F, -3.0F, 12.0F);
        this.tail.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, 0.2617993877991494F, 0.0F, 0.0F);
        this.hornLeft2 = new ModelRenderer(this, 30, 26);
        this.hornLeft2.setPos(0.0F, -7.0F, 0.5F);
        this.hornLeft2.addBox(-0.5F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hornLeft2, -0.4363323129985824F, -0.2617993877991494F, 0.0F);
        this.legBack = new ModelRenderer(this, 41, 0);
        this.legBack.setPos(0.0F, 6.0F, 8.0F);
        this.legBack.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 41, 0);
        this.armRight.setPos(-3.5F, 6.0F, -2.0F);
        this.armRight.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 10.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.hornLeft = new ModelRenderer(this, 0, 26);
        this.hornLeft.setPos(2.5F, -3.0F, -4.5F);
        this.hornLeft.addBox(-0.5F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hornLeft, 0.2617993877991494F, 0.0F, 0.3490658503988659F);
        this.hornRight2 = new ModelRenderer(this, 30, 26);
        this.hornRight2.setPos(0.0F, -7.0F, 0.5F);
        this.hornRight2.addBox(-1.5F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hornRight2, -0.4363323129985824F, 0.2617993877991494F, 0.0F);
        this.hornRight = new ModelRenderer(this, 0, 26);
        this.hornRight.setPos(-2.5F, -3.0F, -4.5F);
        this.hornRight.addBox(-1.5F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hornRight, 0.2617993877991494F, 0.0F, -0.3490658503988659F);
        this.body.addChild(this.head);
        this.body.addChild(this.hips);
        this.head.addChild(this.dewlap);
        this.body.addChild(this.armLeft);
        this.hips.addChild(this.tail);
        this.hornLeft.addChild(this.hornLeft2);
        this.hips.addChild(this.legBack);
        this.body.addChild(this.armRight);
        this.head.addChild(this.hornLeft);
        this.hornRight.addChild(this.hornRight2);
        this.head.addChild(this.hornRight);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setupAnim(DomesticTribullEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.body.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
        this.tail.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.3F * f1;
        this.hips.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.1F * f1;
        this.legBack.xRot = MathHelper.cos(2.0F + f * speed * 0.4F) * degree * 0.8F * f1;
        this.armRight.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.8F * f1;
        this.armLeft.xRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.8F * f1;
        this.head.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.05F * f1;
        this.tail.xRot = MathHelper.cos(-2.0F + f * speed * 0.4F) * degree * 0.2F * f1 + 0.2F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
