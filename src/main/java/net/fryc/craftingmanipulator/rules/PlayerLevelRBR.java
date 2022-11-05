package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

public class PlayerLevelRBR extends RecipeBlockingRules{

    private final int playerLevel;
    private final boolean belowThisLvl;



    /**
     * Blocks recipes and unlocks them when player has specified level
     *
     * @param tooltip      - additional tooltip that will be displayed when player holds shift
     * @param blockedItems - items that will be blocked with this rule
     * @param playerLevel  - player level needed to unlock items
     * @param belowThisLvl - when true, players level must be lower than specified level to craft item
     */
    public PlayerLevelRBR(String tooltip, TagKey<Item> blockedItems, int playerLevel, boolean belowThisLvl) {
        super(tooltip, blockedItems);
        this.playerLevel = playerLevel;
        this.belowThisLvl = belowThisLvl;
        this.unlockCondition = UnlockConditions.PLAYER_LEVEL;
    }


    public int getPlayerLevel() {
        return playerLevel;
    }

    public boolean isBelowThisLvl() {
        return belowThisLvl;
    }
}
