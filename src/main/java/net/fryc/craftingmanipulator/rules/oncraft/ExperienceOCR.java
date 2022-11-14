package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
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

    /**
     * Gives player experience (or levels) when player crafts one of specified items and meets requirements
     *
     * @param tooltip      - tooltip that will be added to items specified in rule
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public ExperienceOCR(String tooltip, TagKey<Item> ruleItems, int amount, boolean isExperience, UnlockConditions condition, TagKey<?> neededItems) {
        super(tooltip, ruleItems);
        this.xp = amount;
        this.isExperience = isExperience;
        this.condition = condition;
        this.neededItems = neededItems;
    }

    /**
     * Gives player experience (or levels) when player crafts one of specified items and meets requirements
     *
     * @param tooltip      - tooltip that will be added to items specified in rule
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     * @param requiredLevel - level required to enable this OCR
     */
    public ExperienceOCR(String tooltip, TagKey<Item> ruleItems, int amount, boolean isExperience, int requiredLevel) {
        super(tooltip, ruleItems);
        this.xp = amount;
        this.isExperience = isExperience;
        this.condition = UnlockConditions.PLAYER_LEVEL;
        this.unlockLevel = requiredLevel;
    }


    public int getXp() {
        return xp;
    }

    public boolean isExperience() {
        return isExperience;
    }
}
