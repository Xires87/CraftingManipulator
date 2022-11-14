package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

import java.util.ArrayList;

public class OnCraftRules {

    private final String ruleName;
    private final TagKey<Item> ruleItems;

    protected static ArrayList<OnCraftRules> onCraftRules = new ArrayList<OnCraftRules>();

    protected UnlockConditions condition;
    protected TagKey<?> neededItems;
    protected int unlockLevel;
    private boolean isReversed = false;

    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }


    /**
     * @param tooltip   - tooltip that will be added to items specified in rule
     * @param ruleItems - items affected by this rule
     */
    protected OnCraftRules(String tooltip, TagKey<Item> ruleItems){
        this.ruleName = tooltip;
        this.ruleItems = ruleItems;
        this.condition = UnlockConditions.NONE;
        this.neededItems = ruleItems;
        this.unlockLevel = 0;
        onCraftRules.add(this);
    }

    public static ArrayList<OnCraftRules> getOnCraftRules() {
        return onCraftRules;
    }

    public String getRuleName() {
        return ruleName;
    }

    public TagKey<Item> getRuleItems() {
        return ruleItems;
    }

    public UnlockConditions getCondition() {
        return condition;
    }

    public TagKey<?> getUnlockItems() {
        return neededItems;
    }

    public int getUnlockLevel(){
        return unlockLevel;
    }

    public boolean isReversed(){
        return isReversed;
    }

}
