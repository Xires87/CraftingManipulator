package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;


public class DurabilityOCR extends OnCraftRules{

    private final int durability;

    /**
     * Items from tag will lose durability when crafted
     *
     * @param tooltip    - tooltip that will be added to items specified in rule
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     */
    public DurabilityOCR(String tooltip, TagKey<Item> ruleItems, int damage) {
        super(tooltip, ruleItems);
        this.durability = damage;
    }

    /**
     * Items from tag will lose durability when crafted and requirements are met
     *
     * @param tooltip    - tooltip that will be added to items specified in rule
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public DurabilityOCR(String tooltip, TagKey<Item> ruleItems, int damage, UnlockConditions condition, TagKey<?> neededItems) {
        super(tooltip, ruleItems);
        this.durability = damage;
        this.condition = condition;
        this.neededItems = neededItems;
    }

    /**
     * Items from tag will lose durability when crafted and requirements are met
     *
     * @param tooltip    - tooltip that will be added to items specified in rule
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     * @param requiredLevel - level required to enable this OCR
     */
    public DurabilityOCR(String tooltip, TagKey<Item> ruleItems, int damage, int requiredLevel) {
        super(tooltip, ruleItems);
        this.durability = damage;
        this.condition = UnlockConditions.PLAYER_LEVEL;
        this.unlockLevel = requiredLevel;
    }

    public int getDurability() {
        return durability;
    }
}
