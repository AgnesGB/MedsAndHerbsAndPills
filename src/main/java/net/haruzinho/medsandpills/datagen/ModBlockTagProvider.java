package net.haruzinho.medsandpills.datagen;

import net.haruzinho.medsandpills.MedsAndPills;
import net.haruzinho.medsandpills.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MedsAndPills.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Exemplo: tag para minerais que podem ser minerados com picareta de ferro
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
        //.add(ModBlocks.EXAMPLE_BLOCK.get())
        ;

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.VALERIAN_CROP.get());

        this.tag(BlockTags.CROPS)
                .add(ModBlocks.VALERIAN_CROP.get());
    }
}