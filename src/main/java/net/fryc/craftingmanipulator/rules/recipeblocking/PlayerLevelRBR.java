package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.util.ConditionsHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class PlayerLevelRBR extends RecipeBlockingRules {

    private int playerLevel;

    /**
     * Blocks recipes and unlocks them when player has specified level
     * @param blockedItems - items that will be blocked with this rule
     * @param playerLevel  - player level needed to unlock items
     */
    public PlayerLevelRBR(@Nullable TagKey<Item> blockedItems, int playerLevel) {
        super(blockedItems);
        this.playerLevel = playerLevel;
    }

    /**
     * Blocks recipes and unlocks them when player has specified level
     * @param playerLevel  - player level needed to unlock items
     */
    public PlayerLevelRBR(int playerLevel) {
        this(null, playerLevel);
    }


    public int getRequiredLevel() {
        return playerLevel;
    }
    public void setRequiredLevel(int level) {
        this.playerLevel = level;
    }


    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.playerHasLevel(player, this.playerLevel)){
                craftedItem = this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
            else {
                craftedItem = this.isReversed() ? ItemStack.EMPTY : craftedItem;
            }

            this.drawRedCrossWhenNeeded(craftedItem, player, handler);
        }
        return craftedItem;
    }
}
