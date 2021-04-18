package com.github.scorchedpsyche.resource_extractor;

import com.github.scorchedpsyche.resource_extractor.main.Commands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

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
