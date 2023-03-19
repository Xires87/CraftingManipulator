package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class StandNearBlockRBR extends RecipeBlockingRules{

    private final TagKey<Block> neededBlocks;


    /**
     * Blocks recipes and unlocks them when player stands near required block(-s)
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededBlocks - block(-s) you have to stand nearby to unlock recipe for items blocked by this rule (TagKey<`Block>)
     */
    public StandNearBlockRBR(TagKey<Item> blockedItems, TagKey<Block> neededBlocks) {
        super(blockedItems);
        this.neededBlocks = neededBlocks;
        this.unlockCondition = UnlockConditions.BLOCK_NEARBY;
    }

    public TagKey<Block> getNeededBlocks() {
        return neededBlocks;
    }
}
