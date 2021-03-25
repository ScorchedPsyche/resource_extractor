package com.github.scorchedpsyche.resource_extractor.mixins;

import com.github.scorchedpsyche.resource_extractor.Render;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//INVOKEVIRTUAL net/minecraft/client/gl/Framebuffer.beginWrite (Z)V
//L34
//LINENUMBER 1064 L34
//INVOKESTATIC net/minecraft/client/render/BackgroundRenderer.method_23792 ()V

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BackgroundRenderer;method_23792()V",
            ordinal = 0), method = "render(Z)V")
    private void onRender(CallbackInfo ci){
        if(Render.RenderIcon.doIconRender){
            Render.RenderIcon.doRender();
        }
    }
}
