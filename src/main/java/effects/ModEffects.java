package effects;

import net.haruzinho.medsandpills.MedsAndPills;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MedsAndPills.MOD_ID);
    public static final RegistryObject<PainEffect> PAIN = EFFECTS.register("pain", PainEffect::new);
}