package net.haruzinho.medsandpills.itens;

import net.haruzinho.medsandpills.MedsAndPills;
import net.haruzinho.medsandpills.itens.custom.PainKillerPill;
import net.minecraft.world.item.Item;
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

    //plantas novas:


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
