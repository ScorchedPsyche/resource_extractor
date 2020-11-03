package com.github.scorchedpsyche.resource_extractor.mixins;

import com.github.scorchedpsyche.resource_extractor.interfaces.IItemsList;
import com.google.common.collect.BiMap;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> implements IItemsList
{

    @Shadow
    protected BiMap<Identifier, T> idToEntry;

    public int getSize(){
        return idToEntry.size();
    }
}
