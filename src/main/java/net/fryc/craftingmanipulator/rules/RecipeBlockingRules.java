package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

import java.util.*;

public class RecipeBlockingRules {

    private final String ruleName;
    private final TagKey<Item> blockedItems;
    UnlockConditions unlockCondition;

    protected static ArrayList<RecipeBlockingRules> recipeBlockingRules = new ArrayList<RecipeBlockingRules>();

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


}
