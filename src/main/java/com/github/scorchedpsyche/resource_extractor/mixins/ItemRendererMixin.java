package com.github.scorchedpsyche.resource_extractor.mixins;

import com.github.scorchedpsyche.resource_extractor.Render;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//FRAME APPEND [I]
//    ALOAD 0
//    ALOAD 1
//    GETSTATIC net/minecraft/client/render/model/json/ModelTransformation$Mode.GUI : Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;
//    ICONST_0
//    ALOAD 5
//    ALOAD 6
//    LDC 15728880
//    GETSTATIC net/minecraft/client/render/OverlayTexture.DEFAULT_UV : I
//    ALOAD 4
//    INVOKEVIRTUAL net/minecraft/client/render/item/ItemRenderer.renderItem (Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V


//L19
//    LINENUMBER 248 L19
//    FRAME APPEND [I]
//    ALOAD 0
//    ALOAD 1
//    GETSTATIC net/minecraft/client/render/model/json/ModelTransformation$Mode.GUI : Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;
//    ICONST_0
//    ALOAD 5
//    ALOAD 6
//    LDC 15728880
//    GETSTATIC net/minecraft/client/render/OverlayTexture.DEFAULT_UV : I
//    ALOAD 4
//    INVOKEVIRTUAL net/minecraft/client/render/item/ItemRenderer.renderItem (Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Inject(method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem" +
                    "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", ordinal = 0))
    private void onRenderGuiItemModelScale(CallbackInfo ci){
        if(Render.RenderIcon.doIconRender){
            if(Render.RenderIcon.selectedSize != 0){
                RenderSystem.getModelViewStack().push();

                RenderSystem.getModelViewStack().scale(
                        Render.RenderIcon.scale[Render.RenderIcon.selectedSize],
                        Render.RenderIcon.scale[Render.RenderIcon.selectedSize],
                        Render.RenderIcon.scale[Render.RenderIcon.selectedSize]
                );

                RenderSystem.applyModelViewMatrix();
            }
        }
    }
}
