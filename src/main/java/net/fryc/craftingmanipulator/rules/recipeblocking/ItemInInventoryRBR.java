package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.util.ConditionsHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class ItemInInventoryRBR extends RecipeBlockingRules implements HasUnlockCondition<Item> {

    @Nullable
    private final TagKey<Item> neededItems;

    @Nullable
    private HashSet<Item> additionalNeededItems;

    /**
     * Blocks recipes and unlocks them when player has one of the items specified in tag or HashSet
     * @param blockedItems - items blocked with this rule
     * @param neededItems - items required to unlock recipe for items blocked by this rule
     */
    public ItemInInventoryRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Item> neededItems) {
        super(blockedItems);
        this.neededItems = neededItems;
    }

    /**
     * Blocks recipes and unlocks them when player has one of the items specified in HashSet
     */
    public ItemInInventoryRBR() {
        this(null, null);
    }


    @Override
    public @Nullable TagKey<Item> getUnlockTag() {
        return this.neededItems;
    }

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
    public ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.hasCorrectItemInInventory(player, this.neededItems, this.additionalNeededItems)){
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
