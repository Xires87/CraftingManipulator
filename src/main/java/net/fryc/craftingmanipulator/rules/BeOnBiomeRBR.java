package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class BeOnBiomeRBR extends RecipeBlockingRules{

    private final TagKey<Biome> neededBiomes;

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @deprecated - use tooltip rule to append tooltip
     * @param tooltip - tooltip displayed on blocked items. Leave it empty ("") if you don't want to display tooltip (String)
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule (TagKey<`Biome>)
     */
    @Deprecated
    public BeOnBiomeRBR(String tooltip, TagKey<Item> blockedItems, TagKey<Biome> neededBiomes) {
        super(tooltip, blockedItems);
        this.neededBiomes = neededBiomes;
        this.unlockCondition = UnlockConditions.ON_BIOME;
    }

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule (TagKey<`Biome>)
     */
    public BeOnBiomeRBR(TagKey<Item> blockedItems, TagKey<Biome> neededBiomes) {
        super(blockedItems);
        this.neededBiomes = neededBiomes;
        this.unlockCondition = UnlockConditions.ON_BIOME;
    }

    public TagKey<Biome> getNeededBiomes() {
        return neededBiomes;
    }
}
