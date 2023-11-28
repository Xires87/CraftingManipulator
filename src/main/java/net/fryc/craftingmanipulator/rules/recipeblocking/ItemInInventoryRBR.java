package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class ItemInInventoryRBR extends RecipeBlockingRules implements AdditionalNeededThings{

    @Nullable
    private final TagKey<Item> neededItems;
    /**
     * For making multiple rules use the same HashSet.
     *  Use it only as setter
     */
    @Nullable
    public HashSet<Item> additionalNeededItems;

    /**
     * Blocks recipes and unlocks them when player has required item(-s) in inventory
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededItems - items required to unlock recipe for items blocked by this rule (TagKey<`Item>)
     */
    public ItemInInventoryRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Item> neededItems) {
        super(blockedItems);
        this.unlockCondition = UnlockConditions.ITEM_IN_INVENTORY;
        this.neededItems = neededItems;
    }

    public @Nullable TagKey<Item> getNeededItems() {
        return this.neededItems;
    }

    @Override
    public boolean conditionsAreMet(PlayerEntity player){
        boolean returnValue = false;
        if(this.getNeededItems() != null){
            returnValue = ConditionsHelper.hasCorrectItemInInventory(player, this.getNeededItems());
        }
        if(!returnValue && !this.isAdditionalNeededThingsNull()) {
            returnValue = ConditionsHelper.hasCorrectItemInInventory(player, this.getAdditionalNeededThings());
        }

        if(this.isReversed()){
            returnValue = !returnValue;
        }
        return returnValue;
    }

    /**
     * If additionalNeededItems is null, a new HashSet will be assigned to it
     * @return additionalNeededItems
     */
    @Override
    public @NotNull HashSet<Item> getAdditionalNeededThings() {
        if(this.additionalNeededItems == null){
            this.additionalNeededItems = new HashSet<>();
        }
        return this.additionalNeededItems;
    }

    /**
     * @return true when additionalNeededItems is null
     */
    @Override
    public boolean isAdditionalNeededThingsNull() {
        return this.additionalNeededItems == null;
    }
}
