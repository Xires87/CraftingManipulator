package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.util.ConditionsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class StandNearBlockRBR extends RecipeBlockingRules implements HasUnlockCondition<Block> {

    @Nullable
    private final TagKey<Block> unlockBlocks;
    @Nullable
    private HashSet<Block> additionalUnlockBlocks;


    /**
     * Blocks recipes and unlocks them when player stands near one of the blocks specified in tag or HashSet
     * @param blockedItems - items blocked by this rule
     * @param unlockBlocks - block(-s) you have to stand nearby to unlock recipe for items blocked by this rule
     */
    public StandNearBlockRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Block> unlockBlocks) {
        super(blockedItems);
        this.unlockBlocks = unlockBlocks;
    }

    /**
     * Blocks recipes and unlocks them when player stands near one of the blocks specified in HashSet
     */
    public StandNearBlockRBR() {
        this(null, null);
    }

    @Nullable
    public TagKey<Block> getUnlockTag() {
        return this.unlockBlocks;
    }



    @Override
    public HashSet<Block> getOrCreateUnlockThings() {
        if(this.additionalUnlockBlocks == null){
            this.additionalUnlockBlocks = new HashSet<>();
        }
        return this.additionalUnlockBlocks;
    }

    public void setUnlockThings(@Nullable HashSet<Block> additionalAffectedBlocks) {
        this.additionalUnlockBlocks = additionalAffectedBlocks;
    }


    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.standsNearCorrectBlock(player.getBlockPos(), world, this.unlockBlocks, this.additionalUnlockBlocks)){
                craftedItem = this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
            else {
                craftedItem = this.isReversed() ? ItemStack.EMPTY : craftedItem;
            }

            this.drawRedCrossWhenNeeded(craftedItem, player, handler);
        }


        return craftedItem;
    }

    @Override
    public ItemStack modifyItemCrafterIsAboutToCraft(ItemStack craftedItem, ServerWorld world, BlockState crafterState, BlockPos crafterPos) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.standsNearCorrectBlock(crafterPos, world, this.unlockBlocks, this.additionalUnlockBlocks)){
                craftedItem = this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
            else {
                craftedItem = this.isReversed() ? ItemStack.EMPTY : craftedItem;
            }
        }

        return craftedItem;
    }
}
