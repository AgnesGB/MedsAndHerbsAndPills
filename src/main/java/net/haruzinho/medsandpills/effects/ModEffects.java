package net.haruzinho.medsandpills.effects;

import net.haruzinho.medsandpills.MedsAndPills;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MedsAndPills.MOD_ID);
    public static final RegistryObject<PainEffect> PAIN = EFFECTS.register("pain", PainEffect::new);
    public static final RegistryObject<PainKillerEffect> PAINKILLER = EFFECTS.register("pain_killer", PainKillerEffect::new);
    public static final RegistryObject<AddictionEffect> ADDICTION = EFFECTS.register("addiction", AddictionEffect::new);
}