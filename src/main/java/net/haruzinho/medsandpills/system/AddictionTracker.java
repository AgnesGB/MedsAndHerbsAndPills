package net.haruzinho.medsandpills.system;

import net.haruzinho.medsandpills.effects.ModEffects;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

public class AddictionTracker {
    private static final String TAG_ADDICTION_LEVEL = "addiction_level";
    private static final String TAG_LAST_USE_TIME = "last_analgesic_use";
    private static final String TAG_CONSUMPTION_COUNT = "analgesic_consumption_count";

    public static void trackUse(Player player) {
        CompoundTag data = player.getPersistentData();
        long currentTime = player.level().getGameTime();
        long lastUse = data.getLong(TAG_LAST_USE_TIME);

        // Reset count se passou mais de 2 dias (48k ticks) sem usar
        if (currentTime - lastUse > 48000) {
            data.putInt(TAG_CONSUMPTION_COUNT, 0);
        }

        // Incrementa contador de uso
        int count = data.getInt(TAG_CONSUMPTION_COUNT) + 1;
        data.putInt(TAG_CONSUMPTION_COUNT, count);
        data.putLong(TAG_LAST_USE_TIME, currentTime);

        // Atualiza nível de dependência baseado no consumo
        updateAddictionLevel(player, count);
    }

    private static void updateAddictionLevel(Player player, int consumptionCount) {
        CompoundTag data = player.getPersistentData();
        int currentLevel = data.getInt(TAG_ADDICTION_LEVEL);
        int newLevel = calculateAddictionLevel(consumptionCount);

        if (newLevel > currentLevel) {
            data.putInt(TAG_ADDICTION_LEVEL, newLevel);
            applyAddictionEffect(player, newLevel);
        }
    }

    private static int calculateAddictionLevel(int consumptionCount) {
        if (consumptionCount >= 10) return 3; // Viciado grave
        if (consumptionCount >= 5) return 2;  // Viciado moderado
        if (consumptionCount >= 3) return 1;  // Viciado leve
        return 0;                             // Sem dependência
    }

    private static void applyAddictionEffect(Player player, int level) {
        // Remove efeito antigo se existir
        player.removeEffect(ModEffects.ADDICTION.get());

        // Aplica novo nível de dependência
        if (level > 0) {
            player.addEffect(new MobEffectInstance(
                    ModEffects.ADDICTION.get(),
                    -1, // Duração infinita
                    level - 1, // Níveis começam em 0
                    false, true, true));
        }
    }

    public static int getAddictionLevel(Player player) {
        return player.getPersistentData().getInt(TAG_ADDICTION_LEVEL);
    }

    public static void reduceAddiction(Player player, int amount) {
        CompoundTag data = player.getPersistentData();
        int currentLevel = data.getInt(TAG_ADDICTION_LEVEL);
        int newLevel = Math.max(0, currentLevel - amount);

        if (newLevel < currentLevel) {
            data.putInt(TAG_ADDICTION_LEVEL, newLevel);
            data.putInt(TAG_CONSUMPTION_COUNT, Math.max(0, data.getInt(TAG_CONSUMPTION_COUNT) - (amount * 2)));
            applyAddictionEffect(player, newLevel);
        }
    }
}