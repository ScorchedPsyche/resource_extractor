package com.github.scorchedpsyche.resource_extractor.main;

import net.minecraft.client.MinecraftClient;

@SuppressWarnings("ALL")
public class RenderData {
    MinecraftClient client = MinecraftClient.getInstance();
    SaveRenderToImage saveRenderToImage = new SaveRenderToImage();

    public RenderData(){
    }

    public void Render(String group){
        Utils.say("Rendering §b§l" + group);

        switch(group){
            case "blocks":
                renderBlocks();
                break;

            case "entities":
                renderEntities();
                break;

            case "items":
                renderItems();
                break;

            default: // "all"
                renderAll();
                break;
        }

        Utils.say("Finished rendering!");
    }

    public void renderAll(){
        renderBlocks();
        renderEntities();
        renderItems();
    }

    public void renderBlocks(){

    }

    public void renderEntities(){

    }

    public void renderItems(){
        Render.RenderIcon.doIconRender = true;
    }
}