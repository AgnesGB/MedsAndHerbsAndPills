package net.haruzinho.medsandpills.mixins;

import net.haruzinho.medsandpills.init.ModItems;
import net.haruzinho.medsandpills.itens.ModItems;
import net.mcreator.medsandherbs.init.MedsAndHerbsModItems;
import net.mcreator.medsandherbs.procedures.ExtractsRMBProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ExtractsRMBProcedure.class)
public abstract class ExtractsRMBMixin {

    @Inject(
            method = "execute",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
                    ordinal = 0, // Ajuste este ordinal conforme necessário
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private static void onExtractCheck(@Nullable Event event, Entity entity, CallbackInfo ci) {
        if (entity != null) {
            LivingEntity livingEntity = (LivingEntity) entity;
            ItemStack offhandItem = livingEntity.getOffhandItem();

            // Verifica se é o extract de valeriana
            if (offhandItem.getItem() == net.haruzinho.medsandpills.itens.ModItems.VALERIAN_EXTRACT.get()) {
                if (entity instanceof Player player) {
                    // Cria a seringa com o extrato
                    ItemStack syringe = new ItemStack(net.haruzinho.medsandpills.itens.ModItems.SYRINGE_VALERIAN.get());
                    syringe.setCount(1);

                    // Entrega ao jogador
                    ItemHandlerHelper.giveItemToPlayer(player, syringe);

                    // Remove a seringa vazia da mão principal
                    player.getInventory().clearOrCountMatchingItems(
                            p -> p.getItem() == MedsAndHerbsModItems.SYRINGE_EMPTY.get(),
                            1,
                            player.inventoryMenu.getCraftSlots()
                    );

                    // Incrementa o contador CMD
                    CompoundTag tag = offhandItem.getOrCreateTag();
                    tag.putDouble("CMD", tag.getDouble("CMD") + 1.0);

                    // Se chegou a 3 usos, substitui por garrafa vazia
                    if (tag.getDouble("CMD") >= 3.0) {
                        livingEntity.setItemInHand(
                                InteractionHand.OFF_HAND,
                                new ItemStack(MedsAndHerbsModItems.EMPTY_BOTTLE_DIRTY.get())
                        );
                    }

                    ci.cancel(); // Cancela a execução original
                }
            }
        }
    }
}