package net.fryc.craftingmanipulator.client;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Environment(EnvType.CLIENT)
@Deprecated
public class TooltipRules {
    @Nullable
    private final TagKey<Item> affectedItems;

    @Nullable
    private HashSet<Item> additionalAffectedItems;

    @NotNull
    private final Text tooltip;

    private final PressedKey pressedKey;

    @Nullable
    private final Text tooltipWhenKeyPressed;

    private static final ArrayList<TooltipRules> tooltipRules = new ArrayList<TooltipRules>();

    public boolean forceAddingTooltip = false;

    public boolean isEnabled = true;


    /**
     * Adds tooltip to items specified in tag
     * @param affectedItems - tag containing items affected by this rule
     * @param tooltip - tooltip that will be added to items from tag
     */
    public TooltipRules(@Nullable TagKey<Item> affectedItems, @NotNull Text tooltip){
        this.affectedItems = affectedItems;
        this.tooltip = tooltip;
        this.pressedKey = PressedKey.NONE;
        this.tooltipWhenKeyPressed = null;
        tooltipRules.add(this);
    }


    /**
     * Adds tooltip to items specified in tag and replaces this tooltip with another when player holds specified key (SHIFT, CTRL or ALT)
     * @param affectedItems - tag containing items affected by this rule
     * @param tooltip - tooltip that will be added to items from tag
     * @param key - key that needs to be pressed to replace first tooltip with second tooltip
     * @param tooltipWhenKeyPressed - tooltip that will be displayed when player holds specified key
     */
    public TooltipRules(@Nullable TagKey<Item> affectedItems, @NotNull Text tooltip, PressedKey key, @Nullable Text tooltipWhenKeyPressed){
        this.affectedItems = affectedItems;
        this.tooltip = tooltip;
        this.pressedKey = key;
        this.tooltipWhenKeyPressed = tooltipWhenKeyPressed;
        tooltipRules.add(this);
    }


    public static ArrayList<TooltipRules> getTooltipRules() {
        return tooltipRules;
    }

    public @Nullable TagKey<Item> getAffectedItems() {
        return affectedItems;
    }

    public @NotNull Text getTooltip() {
        return tooltip;
    }

    public PressedKey getPressedKey(){
        return pressedKey;
    }

    public boolean isPressingSelectedKey() {
        return this.pressedKey.isPressingKey();
    }

    public @Nullable Text getTooltipWhenKeyPressed() {
        return tooltipWhenKeyPressed;
    }

    public @NotNull HashSet<Item> getAdditionalAffectedItems(){
        if(this.additionalAffectedItems == null){
            this.additionalAffectedItems = new HashSet<>();
        }
        return this.additionalAffectedItems;
    }

    public void setAdditionalAffectedItems(@Nullable HashSet<Item> additionalAffectedItems) {
        this.additionalAffectedItems = additionalAffectedItems;
    }

    public boolean areAdditionalAffectedItemsNull(){
        return this.additionalAffectedItems == null;
    }

    public void applyWhenPossible(List<Text> tooltip) {
        if(this.isPressingSelectedKey()){
            if(this.getTooltipWhenKeyPressed() != null){
                if(this.forceAddingTooltip || dontHaveDuplicates(tooltip, this.getTooltipWhenKeyPressed())){
                    tooltip.add(this.getTooltipWhenKeyPressed());
                }
            }
        }
        else {
            if(this.forceAddingTooltip || dontHaveDuplicates(tooltip, this.getTooltip())){
                tooltip.add(this.getTooltip());
            }
        }
    }

    public static boolean dontHaveDuplicates(List<Text> list, Text text){
        for(Text tx : list){
            if(tx.contains(text)) return false;
        }
        return true;
    }
}
