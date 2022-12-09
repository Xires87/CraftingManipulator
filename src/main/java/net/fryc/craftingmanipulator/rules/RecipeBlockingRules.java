package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.*;

public class RecipeBlockingRules {

    private final String ruleName;
    private final TagKey<Item> blockedItems;
    protected UnlockConditions unlockCondition;
    private boolean isReversed = false;
    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }

    protected static ArrayList<RecipeBlockingRules> recipeBlockingRules = new ArrayList<RecipeBlockingRules>();

    /**
     * Blocks recipes (they can't be unlocked) and appends tooltip "Uncraftable"
     * @param tooltip - additional tooltip that will be displayed when player holds shift
     * @param blockedItems - items that will be blocked with this rule
     */
    public RecipeBlockingRules(String tooltip, TagKey<Item> blockedItems){
        this.ruleName = tooltip;
        this.blockedItems = blockedItems;
        this.unlockCondition = UnlockConditions.NONE;
        recipeBlockingRules.add(this);
    }

    public static List<RecipeBlockingRules> getRecipeBlockingRules(){
        return recipeBlockingRules;
    }


    public String getRuleName() {
        return ruleName;
    }

    public TagKey<Item> getBlockedItems() {
        return blockedItems;
    }

    public UnlockConditions getUnlockCondition() {
        return this.unlockCondition;
    }

    public boolean isReversed(){
        return isReversed;
    }


}
