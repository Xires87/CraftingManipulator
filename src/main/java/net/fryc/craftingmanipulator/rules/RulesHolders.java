package net.fryc.craftingmanipulator.rules;

import com.google.common.collect.Maps;
import net.fryc.craftingmanipulator.CraftingManipulator;

import java.util.HashMap;
import java.util.Objects;


public class RulesHolders {

    public static final HashMap<String, CraftingRule> CRAFTING_RULES = Maps.newHashMap();

    public static CraftingRule registerCraftingRule(String id, CraftingRule rule){
        Objects.requireNonNull(id);
        Objects.requireNonNull(rule);

        if(CRAFTING_RULES.containsKey(id)){
            CraftingManipulator.LOGGER.error("A rule with the following id: " + id + " is already registered");
            CraftingManipulator.LOGGER.error("Returning existing Crafting Rule: " + id + " : " + CRAFTING_RULES.get(id).getClass().toString());
        }
        else {
            CRAFTING_RULES.put(id, rule);
        }
        return CRAFTING_RULES.get(id);
    }
}
