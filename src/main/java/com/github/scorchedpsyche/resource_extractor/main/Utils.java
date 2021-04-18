package com.github.scorchedpsyche.resource_extractor.main;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Utils {
    MinecraftClient client = MinecraftClient.getInstance();

    public static void say(String text){
        MinecraftClient.getInstance().getServer().getCommandManager().execute(
                MinecraftClient.getInstance().getServer().getCommandSource(),
                "say [Resource Extractor] " + text );
    }

    public static void overlayMessage(String message){
        MinecraftClient.getInstance().inGameHud.setOverlayMessage(Text.of(message), false);
    }
}
