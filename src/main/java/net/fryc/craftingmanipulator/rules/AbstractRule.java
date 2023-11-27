package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.HashSet;

public abstract class AbstractRule implements ConditionChecker{

    private final TagKey<Item> affectedItems;

    private final HashSet<Item> additionalAffectedItems;

    protected UnlockConditions unlockCondition = UnlockConditions.NONE;
    private boolean isReversed = false;

    protected AbstractRule(TagKey<Item> affectedItems){
        this.affectedItems = affectedItems;
        this.additionalAffectedItems = new HashSet<>();
    }


    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }

    public boolean isReversed(){
        return this.isReversed;
    }

    public TagKey<Item> getAffectedItems(){
        return this.affectedItems;
    }

    public HashSet<Item> getAdditionalAffectedItems(){
        return this.additionalAffectedItems;
    }

    public UnlockConditions getUnlockCondition(){
        return this.unlockCondition;
    }
}
