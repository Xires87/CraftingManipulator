package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class PlayerLevelRBR extends RecipeBlockingRules{

    private final int playerLevel;

    /**
     * Blocks recipes and unlocks them when player has specified level
     * @param blockedItems - items that will be blocked with this rule
     * @param playerLevel  - player level needed to unlock items
     */
    public PlayerLevelRBR(TagKey<Item> blockedItems, int playerLevel) {
        super(blockedItems);
        this.playerLevel = playerLevel;
        this.unlockCondition = UnlockConditions.PLAYER_LEVEL;
    }


    public int getPlayerLevel() {
        return playerLevel;
    }

    @Override
    public boolean conditionsAreMet(PlayerEntity player){
        return (ConditionsHelper.playerHasLevel(player, this.getPlayerLevel()) && !this.isReversed()) ||
                (!ConditionsHelper.playerHasLevel(player, this.getPlayerLevel()) && this.isReversed());
    }

}
