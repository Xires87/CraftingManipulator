package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class StandNearBlockRBR extends RecipeBlockingRules implements AdditionalNeededThings{

    @Nullable
    private final TagKey<Block> neededBlocks;
    @Nullable
    private HashSet<Block> additionalNeededBlocks;


    /**
     * Blocks recipes and unlocks them when player stands near required block(-s)
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededBlocks - block(-s) you have to stand nearby to unlock recipe for items blocked by this rule (TagKey<`Block>)
     */
    public StandNearBlockRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Block> neededBlocks) {
        super(blockedItems);
        this.neededBlocks = neededBlocks;
        this.unlockCondition = UnlockConditions.BLOCK_NEARBY;
    }

    @Nullable
    public TagKey<Block> getNeededBlocks() {
        return this.neededBlocks;
    }

    @Override
    public boolean conditionsAreMet(PlayerEntity player){
        boolean returnValue = false;
        if(this.getNeededBlocks() != null){
            returnValue = ConditionsHelper.standsNearCorrectBlock(player, player.getWorld(), this.getNeededBlocks());
        }
        if(!returnValue && !this.isAdditionalNeededThingsNull()) {
            returnValue = ConditionsHelper.standsNearCorrectBlock(player, player.getWorld(), this.getAdditionalNeededThings());
        }

        if(this.isReversed()){
            returnValue = !returnValue;
        }
        return returnValue;
    }

    public void setAdditionalNeededThings(@Nullable HashSet<Block> additionalNeededBlocks) {
        this.additionalNeededBlocks = additionalNeededBlocks;
    }


    /**
     * If additionalNeededBlocks is null, a new HashSet will be assigned to it
     * @return additionalNeededBlocks
     */
    @Override
    public @NotNull HashSet<Block> getAdditionalNeededThings() {
        if(this.additionalNeededBlocks == null){
            this.additionalNeededBlocks = new HashSet<>();
        }
        return this.additionalNeededBlocks;
    }

    /**
     * @return true when additionalNeededBlocks is null
     */
    @Override
    public boolean isAdditionalNeededThingsNull() {
        return this.additionalNeededBlocks == null;
    }
}
