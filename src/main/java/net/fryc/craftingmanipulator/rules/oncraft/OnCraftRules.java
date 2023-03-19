package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;

import java.util.ArrayList;

public class OnCraftRules {

    private final String ruleName;
    private final TagKey<Item> ruleItems;

    protected static ArrayList<OnCraftRules> onCraftRules = new ArrayList<OnCraftRules>();

    protected UnlockConditions condition;
    protected TagKey<?> neededItems;
    protected int unlockLevel;
    private boolean isReversed = false;



    private ArrayList<Class<? extends ScreenHandler>> selectedScreenHandler = new ArrayList<>();
    public ArrayList<Class<? extends ScreenHandler>> getSelectedScreenHandlers() {
        return selectedScreenHandler;
    }
    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }




    /**
     * @param ruleItems - items affected by this rule
     */
    protected OnCraftRules(TagKey<Item> ruleItems){
        this.ruleName = "";
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
