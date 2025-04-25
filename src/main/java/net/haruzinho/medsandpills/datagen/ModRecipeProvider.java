package net.haruzinho.medsandpills.datagen;

import net.haruzinho.medsandpills.itens.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Exemplo de receita para as sementes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VALERIAN_SEEDS.get())
                .requires(ModItems.VALERIAN_ROOTS.get())
                .unlockedBy("has_valerian_roots", has(ModItems.VALERIAN_ROOTS.get()))
                .save(consumer);
    }
}