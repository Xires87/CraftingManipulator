package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

public class AttributeModifierOCR extends OnCraftRules{

    private final EntityAttribute attribute;
    private final double value;
    private final EntityAttributeModifier.Operation operation;



    /**
     * Gives an attribute modifier to crafted armor
     *
     * @param tooltip       - tooltip that will be added to items specified in rule
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     */
    public AttributeModifierOCR(String tooltip, TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation) {
        super(tooltip, ruleItems);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
    }

    /**
     * Gives an attribute modifier to armor when player meets specified requirements
     *
     * @param tooltip       - tooltip that will be added to items specified in rule
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public AttributeModifierOCR(String tooltip, TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, UnlockConditions condition, TagKey<?> neededItems) {
        super(tooltip, ruleItems);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
        this.condition = condition;
        this.neededItems = neededItems;
    }

    /**
     * Gives an attribute modifier to armor when player meets specified requirements
     *
     * @param tooltip       - tooltip that will be added to items specified in rule
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     * @param requiredLevel - level required to enable this OCR
     */
    public AttributeModifierOCR(String tooltip, TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, int requiredLevel) {
        super(tooltip, ruleItems);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
        this.condition = UnlockConditions.PLAYER_LEVEL;
        this.unlockLevel = requiredLevel;
    }

    public EntityAttribute getAttribute() {
        return attribute;
    }

    public double getValue() {
        return value;
    }

    public EntityAttributeModifier.Operation getOperation() {
        return operation;
    }
}
