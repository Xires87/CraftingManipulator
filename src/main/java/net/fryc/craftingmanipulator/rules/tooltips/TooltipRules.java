package net.fryc.craftingmanipulator.rules.tooltips;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fryc.craftingmanipulator.conditions.PressedKey;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Formatting;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class TooltipRules {

    private final TagKey<Item> affectedItems;

    private final String tooltip;

    private final PressedKey pressedKey;

    private final String tooltipWhenKeyPressed;

    private static ArrayList<TooltipRules> tooltipRules = new ArrayList<TooltipRules>();

    public Formatting[] tooltipFormatting = new Formatting[]{};

    public Formatting[] tooltipWhenKeyPressedFormatting = new Formatting[]{};

    public boolean forceAddingTooltip = false;


    /**
     * Adds tooltip to items specified in tag
     * @param affectedItems - tag containing items affected by this rule
     * @param tooltip - tooltip that will be added to items from tag
     */
    public TooltipRules(TagKey<Item> affectedItems, String tooltip){
        this.affectedItems = affectedItems;
        this.tooltip = tooltip;
        this.pressedKey = PressedKey.NONE;
        this.tooltipWhenKeyPressed = "";
        tooltipRules.add(this);
    }


    /**
     * Adds tooltip to items specified in tag and replaces this tooltip with another when player holds specified key (SHIFT, CTRL or ALT)
     * @param affectedItems - tag containing items affected by this rule
     * @param tooltip - tooltip that will be added to items from tag
     * @param key - key that needs to be pressed to replace first tooltip with second tooltip
     * @param tooltipWhenKeyPressed - tooltip that will be displayed when player holds specified key
     */
    public TooltipRules(TagKey<Item> affectedItems, String tooltip, PressedKey key, String tooltipWhenKeyPressed){
        this.affectedItems = affectedItems;
        this.tooltip = tooltip;
        this.pressedKey = key;
        this.tooltipWhenKeyPressed = tooltipWhenKeyPressed;
        tooltipRules.add(this);
    }


    public static ArrayList<TooltipRules> getTooltipRules() {
        return tooltipRules;
    }

    public TagKey<Item> getAffectedItems() {
        return affectedItems;
    }

    public String getTooltip() {
        return tooltip;
    }

    public PressedKey getPressedKey(){
        return pressedKey;
    }

    public boolean isPressingSelectedKey() {
        return this.pressedKey.isPressingKey();
    }

    public String getTooltipWhenKeyPressed() {
        return tooltipWhenKeyPressed;
    }

}
