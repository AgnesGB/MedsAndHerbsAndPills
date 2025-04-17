package net.haruzinho.medsandpills.effects;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.function.Consumer;

public class AddictionEffect extends MobEffect {
    public AddictionEffect() {
        super(MobEffectCategory.HARMFUL, 0x8A2BE2); // Cor roxa
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Efeitos de abstinência (a cada 5 segundos)
        if (entity.tickCount % 100 == 0) {
            // Náusea e lentidão
            entity.addEffect(new MobEffectInstance(
                    MobEffects.CONFUSION,
                    100, // 5 segundos
                    0,
                    false, true, true));

            entity.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN,
                    100,
                    amplifier, // Lentidão escala com o nível de dependência
                    false, true, true));

            // Dano periódico em níveis altos
            if (amplifier >= 2) {
                entity.hurt(entity.damageSources().magic(), 1.0F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInGui(MobEffectInstance effect) {
                return true;
            }
        });
    }
}