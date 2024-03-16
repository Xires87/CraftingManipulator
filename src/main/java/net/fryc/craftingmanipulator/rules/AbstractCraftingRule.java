package net.fryc.craftingmanipulator.rules;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public abstract class AbstractCraftingRule implements CraftingRule {

    @Nullable
    private final TagKey<Item> affectedItems;

    @Nullable
    private HashSet<Item> additionalAffectedItems;

    private boolean isReversed = false;
    public boolean enabled = true;

    protected AbstractCraftingRule(@Nullable TagKey<Item> affectedItems){
        this.affectedItems = affectedItems;
    }



    public @Nullable TagKey<Item> getAffectedItems(){
        return this.affectedItems;
    }

    public HashSet<Item> getOrCreateAdditionalAffectedItems(){
        if(this.additionalAffectedItems == null){
            this.additionalAffectedItems = new HashSet<>();
        }
        return this.additionalAffectedItems;
    }

    public void setAdditionalAffectedItems(@Nullable HashSet<Item> additionalAffectedItems) {
        this.additionalAffectedItems = additionalAffectedItems;
    }


    public void setReversed(boolean reverse){
        this.isReversed = reverse;
    }

    public boolean isReversed(){
        return this.isReversed;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled && (this.affectedItems != null || this.additionalAffectedItems != null);
    }

    public boolean isItemAffectedByThisRule(ItemStack stack){
        if(this.isEnabled() && !stack.isEmpty()){
            boolean returnValue = false;
            if(this.getAffectedItems() != null){
                returnValue = stack.isIn(this.getAffectedItems());
            }
            if(!returnValue && this.additionalAffectedItems != null){
                returnValue = this.additionalAffectedItems.contains(stack.getItem());
            }
            return returnValue;
        }
        return false;
    }
}
