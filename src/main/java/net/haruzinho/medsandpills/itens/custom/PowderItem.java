package net.haruzinho.medsandpills.itens.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class PowderItem extends Item {
    public PowderItem() {
        super((new Item.Properties()).stacksTo(64).rarity(Rarity.COMMON));
    }
}
