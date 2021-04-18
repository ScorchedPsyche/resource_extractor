package com.github.scorchedpsyche.resource_extractor.main;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class RenderData {
    MinecraftClient client = MinecraftClient.getInstance();
    SaveRenderToImage saveRenderToImage = new SaveRenderToImage();

    public RenderData(){
    }

    public void Render(String group){
        Utils.say(Formatting.RED + "\nSCREEN WILL GO BLACK WHILE RENDERING! WAIT FOR IT TO FINISH!");
        Utils.say(Formatting.RED + "\nWE WILL AUTOMATICALLY TOGGLE FULL SREEN IF NEEDED.");
        Utils.say(Formatting.YELLOW +
                "\nThe task will start in 15 seconds. Your monitor must be capable of a minimum dimension " +
                "of 1024x1024 so that there's no clipping on the images.");
        Utils.say("Rendering " + Formatting.AQUA + group);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
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
            }
        }, 15, TimeUnit.SECONDS);
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