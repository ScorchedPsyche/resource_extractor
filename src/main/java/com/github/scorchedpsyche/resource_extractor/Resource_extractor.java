package com.github.scorchedpsyche.resource_extractor;

import net.fabricmc.api.ModInitializer;

public class Resource_extractor implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        System.out.println("Data and Assets Extractor mod loading...");

        Commands commands = new Commands();
        commands.Register();

        System.out.println("Data and Assets Extractor mod finished loading...");
    }
}
