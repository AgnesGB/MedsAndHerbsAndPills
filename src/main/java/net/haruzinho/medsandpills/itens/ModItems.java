package net.haruzinho.medsandpills.itens;

import net.haruzinho.medsandpills.MedsAndPills;
import net.haruzinho.medsandpills.blocks.ModBlocks;
import net.haruzinho.medsandpills.itens.custom.PainKillerPill;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MedsAndPills.MOD_ID);

    public static final RegistryObject<Item> EMPTY_PILL = ITEMS.register("empty_pill",
            () -> new Item(new Item.Properties()));

    //analgesico stuff
    public static final RegistryObject<Item> ANALGESIC_PILL = ITEMS.register("analgesic_pill",
            () -> new PainKillerPill(new Item.Properties().stacksTo(16)));

    //powders
    public static final RegistryObject<Item> VALERIAN_POWDER = ITEMS.register("valerian_powder",
            () -> new Item(new Item.Properties()));

    //plantas novas:
    public static final RegistryObject<Item> VALERIAN_ROOTS = ITEMS.register("valerian_roots",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VALERIAN_SEEDS = ITEMS.register("valerian_seeds",
            () -> new ItemNameBlockItem(ModBlocks.VALERIAN_CROP.get(), new Item.Properties()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
