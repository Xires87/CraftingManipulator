package net.fryc.craftingmanipulator.registry;

import net.fryc.craftingmanipulator.CraftingManipulator;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.rules.CraftingRule;

import java.util.HashMap;
import java.util.Objects;


public class CMRegistries {

    public static final HashMap<String, CraftingRule> CRAFTING_RULES = new HashMap<>();
    public static final HashMap<String, Drawing> DRAWINGS = new HashMap<>();

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

    public static Drawing registerDrawing(String id, Drawing drawing){
        Objects.requireNonNull(id);
        Objects.requireNonNull(drawing);

        if(DRAWINGS.containsKey(id)){
            CraftingManipulator.LOGGER.error("A drawing with the following id: " + id + " is already registered");
            CraftingManipulator.LOGGER.error("Returning existing Drawing: " + id + " : " + DRAWINGS.get(id).getClass().toString());
        }
        else {
            DRAWINGS.put(id, drawing);
        }
        return DRAWINGS.get(id);
    }
}
