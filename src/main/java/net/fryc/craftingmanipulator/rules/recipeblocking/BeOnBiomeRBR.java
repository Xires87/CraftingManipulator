package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class BeOnBiomeRBR extends RecipeBlockingRules implements AdditionalNeededThings {

    @Nullable
    private final TagKey<Biome> neededBiomes;

    /**
     * For making multiple rules use the same HashSet.
     *  Use it only as setter
     */
    @Nullable
    private HashSet<RegistryKey<Biome>> additionalNeededBiomes;

    /**
     * Blocks recipes and unlocks them when player is on one of the biomes specified in tag
     * @param blockedItems - items blocked with this rule (TagKey<`Item>)
     * @param neededBiomes - biomes you have to be on to unlock recipe for items blocked by this rule (TagKey<`Biome>)
     */
    public BeOnBiomeRBR(@Nullable TagKey<Item> blockedItems, @Nullable TagKey<Biome> neededBiomes) {
        super(blockedItems);
        this.neededBiomes = neededBiomes;
        this.unlockCondition = UnlockConditions.ON_BIOME;
    }

    public @Nullable TagKey<Biome> getNeededBiomes() {
        return this.neededBiomes;
    }

    public void setAdditionalNeededThings(@Nullable HashSet<RegistryKey<Biome>> additionalNeededBiomes) {
        this.additionalNeededBiomes = additionalNeededBiomes;
    }

    @Override
    public boolean conditionsAreMet(PlayerEntity player){
        boolean returnValue = false;
        if(this.getNeededBiomes() != null){
            returnValue = ConditionsHelper.isOnCorrectBiome(player, player.getWorld(), this.getNeededBiomes());
        }
        if(!returnValue && !this.isAdditionalNeededThingsNull()) {
            returnValue = ConditionsHelper.isOnCorrectBiome(player, player.getWorld(), this.getAdditionalNeededThings());
        }

        if(this.isReversed()){
            returnValue = !returnValue;
        }
        return returnValue;
    }

    /**
     * If additionalNeededBiomes is null, a new HashSet will be assigned to it
     * @return additionalNeededBiomes
     */
    @Override
    public @NotNull HashSet<RegistryKey<Biome>> getAdditionalNeededThings() {
        if(this.additionalNeededBiomes == null){
            this.additionalNeededBiomes = new HashSet<>();
        }
        return this.additionalNeededBiomes;
    }

    /**
     * @return true when additionalNeededBiomes is null
     */
    @Override
    public boolean isAdditionalNeededThingsNull() {
        return this.additionalNeededBiomes == null;
    }
}
