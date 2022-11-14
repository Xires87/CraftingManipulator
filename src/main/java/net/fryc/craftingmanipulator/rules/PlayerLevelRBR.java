package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

public class PlayerLevelRBR extends RecipeBlockingRules{

    private final int playerLevel;



    /**
     * Blocks recipes and unlocks them when player has specified level
     *
     * @param tooltip      - tooltip displayed on blocked items. Leave it empty ("") if you don't want to display tooltip
     * @param blockedItems - items that will be blocked with this rule
     * @param playerLevel  - player level needed to unlock items
     */
    public PlayerLevelRBR(String tooltip, TagKey<Item> blockedItems, int playerLevel) {
        super(tooltip, blockedItems);
        this.playerLevel = playerLevel;
        this.unlockCondition = UnlockConditions.PLAYER_LEVEL;
    }


    public int getPlayerLevel() {
        return playerLevel;
    }

}
