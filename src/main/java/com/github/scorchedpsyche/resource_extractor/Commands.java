package com.github.scorchedpsyche.resource_extractor;


import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.*;

public class Commands {
    RenderData data = new RenderData();

    public void Register(){
        CommandRegistry.INSTANCE.register(false, dispatcher ->
            // The root of the command. This must be a literal argument.
            dispatcher.register(CommandManager.literal("dae")
                // Then add an argument named bar that is an integer
                .then(CommandManager.argument("group", string()).suggests(suggestedStrings())
                    // The command to be executed if the command "foo" is entered with the argument "bar"
                    .executes(ctx -> {
                        // Return a result. -1 is failure, 0 is a pass and 1 is success.
                        data.Render(getString(ctx, "group"));

                        return 1;
                    }))
                // The command "foo" to execute if there are no arguments.
                .executes(ctx -> {
                    Utils.say("§c§lSyntax error: missing render group!");
                    return 1;
                })
            )
        );
    }

    public static SuggestionProvider<ServerCommandSource> suggestedStrings() {
        List<String> suggestions = Arrays.asList("all", "blocks", "entities", "items");

        return (ctx, builder) -> getSuggestionsBuilder(builder, suggestions);
    }

    private static CompletableFuture<Suggestions> getSuggestionsBuilder(SuggestionsBuilder builder, List<String> list) {
        String remaining = builder.getRemaining().toLowerCase(Locale.ROOT);

        if(list.isEmpty()) { // If the list is empty then return no suggestions
            return Suggestions.empty(); // No suggestions
        }

        for (String str : list) { // Iterate through the supplied list
            if (str.toLowerCase(Locale.ROOT).startsWith(remaining)) {
                builder.suggest(str); // Add every single entry to suggestions list.
            }
        }
        return builder.buildFuture(); // Create the CompletableFuture containing all the suggestions
    }
}
