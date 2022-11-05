package net.fryc.craftingmanipulator.rules.oncraft;


import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

import java.util.ArrayList;

public class OnCraftRules {

    private final String ruleName;
    private final TagKey<Item> ruleItems;

    protected static ArrayList<OnCraftRules> onCraftRules = new ArrayList<OnCraftRules>();



    /**
     * @param tooltip   - tooltip that will be added to items specified in rule
     * @param ruleItems - items affected by this rule
     */
    protected OnCraftRules(String tooltip, TagKey<Item> ruleItems){
        this.ruleName = tooltip;
        this.ruleItems = ruleItems;
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

}
