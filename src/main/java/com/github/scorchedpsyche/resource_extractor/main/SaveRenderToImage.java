package com.github.scorchedpsyche.resource_extractor.main;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SaveRenderToImage {
    MinecraftClient client = MinecraftClient.getInstance();

    public void saveItem(Item item){

        //client.getItemRenderer().renderGuiItemIcon(new ItemStack(item), 0, 0);
    }
}
