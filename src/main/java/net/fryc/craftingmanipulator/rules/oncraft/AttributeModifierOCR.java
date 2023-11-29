package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;

import java.util.UUID;

/**
 * @deprecated all custom attribute modifiers will disappear
 */
@Deprecated
public class AttributeModifierOCR extends OnCraftRules{

    private final EntityAttribute attribute;
    private final double value;
    private final EntityAttributeModifier.Operation operation;


    /**
     * Gives an attribute modifier to crafted armor
     *
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     */
    public AttributeModifierOCR(TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation) {
        super(ruleItems);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
    }

    /**
     * Gives an attribute modifier to armor when player meets specified requirements
     *
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public AttributeModifierOCR(TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, UnlockConditions condition, TagKey<?> neededItems) {
        super(ruleItems, condition, neededItems);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
    }

    /**
     * Gives an attribute modifier to armor when player meets specified requirements
     *
     * @param ruleItems     - items affected by this rule
     * @param attribute     - attribute that will be added
     * @param value         - attributes value
     * @param operation     - operation
     * @param requiredLevel - level required to enable this OCR
     */
    public AttributeModifierOCR(TagKey<Item> ruleItems, EntityAttribute attribute, double value, EntityAttributeModifier.Operation operation, int requiredLevel) {
        super(ruleItems, requiredLevel);
        this.attribute = attribute;
        this.value = value;
        this.operation = operation;
    }


    public EntityAttribute getAttribute() {
        return this.attribute;
    }

    public double getValue() {
        return this.value;
    }

    public EntityAttributeModifier.Operation getOperation() {
        return this.operation;
    }

    @Override
    public void apply(World world, PlayerEntity player, ItemStack stack) {
        if(stack.getItem() instanceof ArmorItem item){
            setArmorProtection(stack, item);
            stack.addAttributeModifier(this.getAttribute(), new EntityAttributeModifier("Custom modifiers", this.getValue(), this.getOperation()), item.getSlotType());
        }
    }

    @Override
    public boolean canModifyItemStack() {
        return true;
    }

    private static void setArmorProtection(ItemStack stack, ArmorItem item){
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2960207a"),"Armor modifier", item.getMaterial().getProtection(item.getType()), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2950207a"),"Armor toughness", item.getMaterial().getToughness(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2940207a"),"Armor knockback resistance", item.getMaterial().getKnockbackResistance(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
    }
}
