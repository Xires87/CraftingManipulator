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
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class BeOnBiomeRBR extends RecipeBlockingRules implements HasUnlockCondition<Biome> {


    private final TagKey<Biome> neededBiomes;

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @param blockedItems - items blocked with this rule
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule
     */
    public BeOnBiomeRBR(@Nullable TagKey<Item> blockedItems, TagKey<Biome> neededBiomes) {
        super(blockedItems);
        this.neededBiomes = neededBiomes;
    }

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule
     */
    public BeOnBiomeRBR(TagKey<Biome> neededBiomes) {
        this(null, neededBiomes);
    }


    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.isOnCorrectBiome(player, world, this.neededBiomes)){
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
    public TagKey<Biome> getUnlockTag() {
        return this.neededBiomes;
    }

    /**
     *  Does nothing. Use tag
     */
    @Override
    public HashSet<Biome> getOrCreateUnlockThings() {
        return new HashSet<>();
    }

    /**
     *  Does nothing. Use tag
     */
    @Override
    public void setUnlockThings(@Nullable HashSet<Biome> unlockThings) {
    }
}
