package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ItemInInventoryRBR extends RecipeBlockingRules{

    private final TagKey<Item> neededItems;

    /**
     * Blocks recipes and unlocks them when player has required item(-s) in inventory
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededItems - items required to unlock recipe for items blocked by this rule (TagKey<`Item>)
     */
    public ItemInInventoryRBR(TagKey<Item> blockedItems, TagKey<Item> neededItems) {
        super(blockedItems);
        this.unlockCondition = UnlockConditions.ITEM_IN_INVENTORY;
        this.neededItems = neededItems;
    }

    public TagKey<Item> getNeededItems() {
        return neededItems;
    }
}
