package net.fryc.craftingmanipulator.rules.oncraft;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

public class ExperienceOCR extends OnCraftRules{

    private final int xp;
    private final boolean isExperience;

    /**
     * Gives player experience (or levels) when player crafts one of specified items
     *
     * @param tooltip      - tooltip that will be added to items specified in rule
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     */
    public ExperienceOCR(String tooltip, TagKey<Item> ruleItems, int amount, boolean isExperience) {
        super(tooltip, ruleItems);
        this.xp = amount;
        this.isExperience = isExperience;
    }


    public int getXp() {
        return xp;
    }

    public boolean isExperience() {
        return isExperience;
    }
}
