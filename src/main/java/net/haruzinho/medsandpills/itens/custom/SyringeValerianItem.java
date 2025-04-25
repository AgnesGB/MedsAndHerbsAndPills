package net.haruzinho.medsandpills.itens.custom;

import net.mcreator.medsandherbs.procedures.SyringeHitProcedure;
import net.mcreator.medsandherbs.procedures.SyringeUseProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SyringeValerianItem extends Item {
    public SyringeValerianItem() {
        super((new Item.Properties()).stacksTo(4).rarity(Rarity.COMMON));
    }

    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("§7A dose of Belladonna Extract."));
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        SyringeUseProcedure.execute(entity, itemstack);
        return ar;
    }

    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        SyringeHitProcedure.execute(entity, sourceentity, itemstack);
        return retval;
    }
}
