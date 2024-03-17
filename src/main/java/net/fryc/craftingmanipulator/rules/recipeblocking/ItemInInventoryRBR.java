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
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class ItemInInventoryRBR extends RecipeBlockingRules implements HasUnlockCondition<Item> {

    @Nullable
    private final TagKey<Item> neededItems;

    @Nullable
    private HashSet<Item> additionalNeededItems;

    /**
     * Blocks recipes and unlocks them when player has required item(-s) in inventory
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededItems - items required to unlock recipe for items blocked by this rule (TagKey<`Item>)
     */
    public ItemInInventoryRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Item> neededItems) {
        super(blockedItems);
        this.neededItems = neededItems;
    }


    @Override
    public @Nullable TagKey<Item> getUnlockTag() {
        return this.neededItems;
    }

    /**
     * If additionalNeededItems is null, a new HashSet will be assigned to it
     * @return additionalNeededItems
     */
    @Override
    public HashSet<Item> getOrCreateUnlockThings() {
        if(this.additionalNeededItems == null){
            this.additionalNeededItems = new HashSet<>();
        }
        return this.additionalNeededItems;
    }

    @Override
    public void setUnlockThings(@Nullable HashSet<Item> additionalNeededItems) {
        this.additionalNeededItems = additionalNeededItems;
    }

    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.hasCorrectItemInInventory(player, this.neededItems, this.additionalNeededItems)){
                return this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
        }
        return craftedItem;
    }
}
