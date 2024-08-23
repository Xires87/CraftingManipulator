package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.rules.AbstractCraftingRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

abstract class RecipeBlockingRules extends AbstractCraftingRule {

    protected RecipeBlockingRules(@Nullable TagKey<Item> affectedItems) {
        super(affectedItems);
    }

    public void onTakeOutput(ItemStack craftedItem, int amount, PlayerEntity player, World world){
    }

    public void onCraftByCrafter(ItemStack craftedItem, World world){
    }

    public void drawRedCrossWhenNeeded(ItemStack craftedItem, ServerPlayerEntity player, ScreenHandler handler){
        if(craftedItem.isEmpty()){
            if(handler instanceof PlayerScreenHandler){
                this.informAboutItemModification(player, "inventory_red_x");
                //this.drawMouseOverTooltip(player, TOOLTIP_ON_RED_X, 134, 28, 18, 15);
            }
            else {
                this.informAboutItemModification(player, "crafting_red_x");
                //this.drawMouseOverTooltip(player, TOOLTIP_ON_RED_X, 87, 32, 28, 21);
            }
        }
    }
}
