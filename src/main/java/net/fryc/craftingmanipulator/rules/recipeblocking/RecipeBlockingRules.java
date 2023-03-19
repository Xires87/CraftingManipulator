package net.fryc.craftingmanipulator.rules.recipeblocking;

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
     * Blocks recipes forever
     * @param blockedItems - items that will be blocked with this rule
     */
    public RecipeBlockingRules(TagKey<Item> blockedItems){
        this.ruleName = "";
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
