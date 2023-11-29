package net.fryc.craftingmanipulator.rules;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public abstract class AbstractRule implements ConditionChecker{

    @Nullable
    private final TagKey<Item> affectedItems;

    private HashSet<Item> additionalAffectedItems;

    protected UnlockConditions unlockCondition = UnlockConditions.NONE;
    private boolean isReversed = false;

    protected AbstractRule(@Nullable TagKey<Item> affectedItems){
        this.affectedItems = affectedItems;
    }


    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }

    public boolean isReversed(){
        return this.isReversed;
    }

    public @Nullable TagKey<Item> getAffectedItems(){
        return this.affectedItems;
    }

    public HashSet<Item> getAdditionalAffectedItems(){
        if(this.areAdditionalAffectedItemsNull()){
            this.additionalAffectedItems = new HashSet<>();
        }
        return this.additionalAffectedItems;
    }

    public void setAdditionalAffectedItems(HashSet<Item> additionalAffectedItems) {
        this.additionalAffectedItems = additionalAffectedItems;
    }

    public boolean areAdditionalAffectedItemsNull(){
        return this.additionalAffectedItems == null;
    }

    public UnlockConditions getUnlockCondition(){
        return this.unlockCondition;
    }
}