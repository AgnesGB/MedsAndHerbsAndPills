package net.haruzinho.medsandpills.effects;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.function.Consumer;


public class PainKillerEffect extends MobEffect {
        public PainKillerEffect() {
            super(MobEffectCategory.BENEFICIAL, 0xADD8E6); // Cor azul clara
        }

        @Override
        public void applyEffectTick(LivingEntity entity, int amplifier) {
            // 1. Remove o efeito de dor se estiver ativo
            if (entity.hasEffect(ModEffects.PAIN.get())) {
                entity.removeEffect(ModEffects.PAIN.get());
                trackAnalgesicUse(entity); // Opcional: para sistema de dependência
            }

            // 2. Aplica os efeitos benéficos (ocultos)
            if (entity.tickCount % 20 == 0) { // A cada segundo
                // Resistência a dano (ignore 20% do dano + 5% por nível)
                entity.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        30, // 1.5 segundos
                        Math.min(amplifier, 3), // Máximo de nível 3
                        false, false, false)); // Oculto

                // Velocidade aumentada
                entity.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        30,
                        Math.min(amplifier, 2),
                        false, false, false));

                // Força aumentada
                entity.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_BOOST,
                        30,
                        Math.min(amplifier, 1),
                        false, false, false));
            }
        }

        private void trackAnalgesicUse(LivingEntity entity) {
            // Implemente seu sistema de dependência aqui
        }

        @Override
        public boolean isDurationEffectTick(int duration, int amplifier) {
            return true; // Verificar a cada tick
        }

        @Override
        public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
            consumer.accept(new IClientMobEffectExtensions() {
                @Override
                public boolean isVisibleInGui(MobEffectInstance effect) {
                    return true; // Mostra apenas o ícone do analgésico
                }

                @Override
                public boolean isVisibleInInventory(MobEffectInstance effect) {
                    return true;
                }
            });
        }
    }