package com.github.scorchedpsyche.resource_extractor.main;

import net.minecraft.client.MinecraftClient;

public class Extract {
    MinecraftClient client = MinecraftClient.getInstance();

    public void achievements()
    {
        Utils.say("achievements");
    }
}
