package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.rules.AbstractCraftingRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

abstract class RecipeBlockingRules extends AbstractCraftingRule {

    protected RecipeBlockingRules(@Nullable TagKey<Item> affectedItems) {
        super(affectedItems);
    }

    public int modifyAmount(ItemStack craftedItem, int amount, PlayerEntity player, World world){
        return amount;
    }
}
