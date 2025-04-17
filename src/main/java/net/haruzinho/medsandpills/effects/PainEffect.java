package net.haruzinho.medsandpills.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.function.Consumer;

@Mod.EventBusSubscriber
public class PainEffect extends MobEffect {

    public PainEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000);
    }

    //Método que cancela o pulo do jogador
    @SubscribeEvent
    public static void onLivingJump(LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(ModEffects.PAIN.get())) {
            MobEffectInstance effect = entity.getEffect(ModEffects.PAIN.get());
            int amplifier = effect != null ? effect.getAmplifier() : 0;

            // Redução progressiva do pulo
            double reduction = 0.9 - (amplifier * 0.05);
            entity.setDeltaMovement(
                    entity.getDeltaMovement().x,
                    Math.min(entity.getDeltaMovement().y * reduction, 0.9),
                    entity.getDeltaMovement().z
            );
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                40, amplifier, false, false, false));

        entity.addEffect(new MobEffectInstance(
                MobEffects.WEAKNESS,
                40, amplifier, false, false, false));

        entity.addEffect(new MobEffectInstance(
                MobEffects.DIG_SLOWDOWN,
                40, amplifier, false, false, false));
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