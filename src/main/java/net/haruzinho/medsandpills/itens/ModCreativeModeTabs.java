package net.haruzinho.medsandpills.itens;

import net.haruzinho.medsandpills.MedsAndPills;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MedsAndPills.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MED_PILLS_TAB = CREATIVE_MODE_TABS.register("med_pills_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EMPTY_PILL.get()))
                    .title(Component.translatable("creativetab.med_pills_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.EMPTY_PILL.get());
                        pOutput.accept(ModItems.ANALGESIC_PILL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MED_PLANTS_TAB = CREATIVE_MODE_TABS.register("med_plants_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EMPTY_PILL.get()))
                    .title(Component.translatable("creativetab.med_plants_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.VALERIAN_ROOTS.get());
                        pOutput.accept(ModItems.VALERIAN_SEEDS.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
