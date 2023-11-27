package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.fryc.craftingmanipulator.rules.AbstractRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import java.util.*;

public class RecipeBlockingRules extends AbstractRule {
    protected static ArrayList<RecipeBlockingRules> recipeBlockingRules = new ArrayList<RecipeBlockingRules>();


    /**
     * Blocks recipes forever
     * @param blockedItems - items that will be blocked with this rule
     */
    public RecipeBlockingRules(TagKey<Item> blockedItems){
        super(blockedItems);
        recipeBlockingRules.add(this);
    }

    public static List<RecipeBlockingRules> getRecipeBlockingRules(){
        return recipeBlockingRules;
    }



    public boolean conditionsAreMet(PlayerEntity player){
        return false;
    }


}
