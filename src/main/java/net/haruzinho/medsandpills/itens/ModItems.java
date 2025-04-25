package net.haruzinho.medsandpills.itens;

import net.haruzinho.medsandpills.MedsAndPills;
import net.haruzinho.medsandpills.blocks.ModBlocks;
import net.haruzinho.medsandpills.itens.custom.ExtractItem;
import net.haruzinho.medsandpills.itens.custom.PainKillerPill;
import net.haruzinho.medsandpills.itens.custom.PowderItem;
import net.mcreator.medsandherbs.item.ExtractHerbalItem;
import net.mcreator.medsandherbs.procedures.ExtractsCMDCheckProcedure;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.mcreator.medsandherbs.init.MedsAndHerbsModItems.REGISTRY;

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
            () -> new PowderItem());
    public static final RegistryObject<Item> VALERIAN_EXTRACT = ITEMS.register("valerian_extract",
            () -> new ExtractItem());

    //plantas novas:
    public static final RegistryObject<Item> VALERIAN_ROOTS = ITEMS.register("valerian_roots",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VALERIAN_SEEDS = ITEMS.register("valerian_seeds",
            () -> new ItemNameBlockItem(ModBlocks.VALERIAN_CROP.get(), new Item.Properties()));


    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register((Item) VALERIAN_EXTRACT.get(), new ResourceLocation("medsandpills:extract_valerian_extractscmd"), (itemStackToRender, clientWorld, entity, itemEntityId) -> {
                return (float) ExtractsCMDCheckProcedure.execute(itemStackToRender);
            });
        }
    }


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
