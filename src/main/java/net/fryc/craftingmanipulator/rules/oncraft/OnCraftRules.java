package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.fryc.craftingmanipulator.rules.AbstractRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class OnCraftRules extends AbstractRule {

    @Nullable
    protected TagKey<?> neededItems;
    protected int unlockLevel;

    protected static ArrayList<OnCraftRules> onCraftRules = new ArrayList<OnCraftRules>();
    private ArrayList<Class<? extends ScreenHandler>> selectedScreenHandler = new ArrayList<>();



    /**
     * @param ruleItems - items affected by this rule
     */
    protected OnCraftRules(TagKey<Item> ruleItems){
        super(ruleItems);
        this.neededItems = null;
        this.unlockLevel = 0;
        onCraftRules.add(this);
    }

    /**
     * @param ruleItems - items affected by this rule
     * @param condition - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    protected OnCraftRules(TagKey<Item> ruleItems, UnlockConditions condition, TagKey<?> neededItems){
        this(ruleItems);
        this.unlockCondition = condition;
        this.neededItems = neededItems;
    }

    /**
     * @param ruleItems - items affected by this rule
     * @param requiredLevel - level required to enable this OCR
     */
    protected OnCraftRules(TagKey<Item> ruleItems, int requiredLevel){
        this(ruleItems);
        this.unlockLevel = requiredLevel;
        this.unlockCondition = UnlockConditions.PLAYER_LEVEL;
    }


    public static ArrayList<OnCraftRules> getOnCraftRules() {
        return onCraftRules;
    }

    public ArrayList<Class<? extends ScreenHandler>> getSelectedScreenHandlers() {
        return selectedScreenHandler;
    }

    @Nullable
    public TagKey<?> getUnlockItems() {
        return this.neededItems;
    }

    public int getUnlockLevel(){
        return this.unlockLevel;
    }


    @Override
    public boolean conditionsAreMet(PlayerEntity player) {
        return this.getUnlockCondition() == UnlockConditions.NONE || (ConditionsHelper.detectAndUnlock(this.getUnlockCondition(), player, this.getUnlockItems(), this.getUnlockLevel()) && !this.isReversed()) ||
                (!ConditionsHelper.detectAndUnlock(this.getUnlockCondition(), player, this.getUnlockItems(), this.getUnlockLevel()) && this.isReversed());
    }
}
