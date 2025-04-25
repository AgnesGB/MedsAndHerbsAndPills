package net.haruzinho.medsandpills.itens.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ExtractItem extends Item {
    public ExtractItem() {
        super((new Item.Properties()).stacksTo(1).rarity(Rarity.COMMON));
    }
}
