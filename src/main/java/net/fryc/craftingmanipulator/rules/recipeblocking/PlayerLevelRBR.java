package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.util.ConditionsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public class PlayerLevelRBR extends RecipeBlockingRules {

    private int playerLevel;

    /**
     * Blocks recipes and unlocks them when player has specified level
     * @param blockedItems - items that will be blocked with this rule
     * @param playerLevel  - player level needed to unlock items
     */
    public PlayerLevelRBR(TagKey<Item> blockedItems, int playerLevel) {
        super(blockedItems);
        this.playerLevel = playerLevel;
    }


    public int getRequiredLevel() {
        return playerLevel;
    }
    public void setRequiredLevel(int level) {
        this.playerLevel = level;
    }


    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.playerHasLevel(player, this.playerLevel)){
                return this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
        }
        return craftedItem;
    }
}
