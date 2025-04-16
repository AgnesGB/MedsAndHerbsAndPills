package effects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class PainEffect extends MobEffect {
    public PainEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF0000); // Cor vermelha
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Aplica dano a cada 5 segundos (100 ticks)
        if (entity.tickCount % 100 == 0) {
            entity.hurt(entity.damageSources().magic(), 1.0F);
        }

        // Efeitos adicionais
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, amplifier));
        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 50, 0));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
