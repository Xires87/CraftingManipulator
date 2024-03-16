package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class BeOnBiomeRBR extends RecipeBlockingRules implements HasUnlockCondition<Biome> {

    @Nullable
    private final TagKey<Biome> neededBiomes;

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @param blockedItems - items blocked with this rule
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule
     */
    public BeOnBiomeRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Biome> neededBiomes) {
        super(blockedItems);
        this.neededBiomes = neededBiomes;
    }


    @Override
    public ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(!ConditionsHelper.isOnCorrectBiome(player, world, this.neededBiomes)){
                return this.isReversed() ? craftedItem : ItemStack.EMPTY;
            }
        }
        return craftedItem;
    }

    @Override
    public @Nullable TagKey<Biome> getUnlockTag() {
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
