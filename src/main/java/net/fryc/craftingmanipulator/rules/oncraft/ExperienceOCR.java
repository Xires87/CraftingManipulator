package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ExperienceOCR extends OnCraftRules{

    private final int xp;
    private final boolean isExperience;



    /**
     * Gives player experience (or levels) when player crafts one of specified items
     *
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     */
    public ExperienceOCR(TagKey<Item> ruleItems, int amount, boolean isExperience) {
        super(ruleItems);
        this.xp = amount;
        this.isExperience = isExperience;
    }

    /**
     * Gives player experience (or levels) when player crafts one of specified items and meets requirements
     *
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public ExperienceOCR(TagKey<Item> ruleItems, int amount, boolean isExperience, UnlockConditions condition, TagKey<?> neededItems) {
        super(ruleItems, condition, neededItems);
        this.xp = amount;
        this.isExperience = isExperience;
    }

    /**
     * Gives player experience (or levels) when player crafts one of specified items and meets requirements
     *
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     * @param requiredLevel - level required to enable this OCR
     */
    public ExperienceOCR(TagKey<Item> ruleItems, int amount, boolean isExperience, int requiredLevel) {
        super(ruleItems, requiredLevel);
        this.xp = amount;
        this.isExperience = isExperience;
    }


    public int getXp() {
        return this.xp;
    }

    public boolean isExperience() {
        return this.isExperience;
    }
}
