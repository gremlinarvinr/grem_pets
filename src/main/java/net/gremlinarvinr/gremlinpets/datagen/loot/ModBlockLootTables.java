package net.gremlinarvinr.gremlinpets.datagen.loot;

import net.gremlinarvinr.gremlinpets.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        // taking all blocks we've registered in the deferred register and say they are going to be known
    }
}
