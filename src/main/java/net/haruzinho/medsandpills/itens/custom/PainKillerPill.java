package net.haruzinho.medsandpills.itens.custom;

import net.haruzinho.medsandpills.effects.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PainKillerPill extends Item {
        public PainKillerPill(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
            ItemStack stack = player.getItemInHand(hand);

            if (!world.isClientSide) {
                // Aplica o efeito analgésico por 5 minutos (nível 0)
                player.addEffect(new MobEffectInstance(
                        ModEffects.PAINKILLER.get(),
                        6000, // 5 minutos
                        0,    // Nível básico
                        false, false, true));

                // Consome o item
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            return InteractionResultHolder.success(stack);
        }
    }