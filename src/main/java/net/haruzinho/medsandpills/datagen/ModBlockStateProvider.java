package net.haruzinho.medsandpills.datagen;


import net.haruzinho.medsandpills.MedsAndPills;
import net.haruzinho.medsandpills.blocks.ModBlocks;
import net.haruzinho.medsandpills.blocks.custom.ValerianBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MedsAndPills.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeValerianCrop((CropBlock) ModBlocks.VALERIAN_CROP.get(), "valerian_stage", "valerian_stage");
    }


    public void makeValerianCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> valerianStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] valerianStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((ValerianBlock) block).getAgeProperty()),
                new ResourceLocation(MedsAndPills.MOD_ID, "block/" + textureName + state.getValue(((ValerianBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}