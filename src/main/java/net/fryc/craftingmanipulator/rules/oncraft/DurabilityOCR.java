package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;


public class DurabilityOCR extends OnCraftRules{

    private final int damage;

    /**
     * Items from tag will lose durability when crafted
     *
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     */
    public DurabilityOCR(TagKey<Item> ruleItems, int damage) {
        super(ruleItems);
        this.damage = damage;
    }



    /**
     * Items from tag will lose durability when crafted and requirements are met
     *
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public DurabilityOCR(TagKey<Item> ruleItems, int damage, UnlockConditions condition, TagKey<?> neededItems) {
        super(ruleItems, condition, neededItems);
        this.damage = damage;
    }

    /**
     * Items from tag will lose durability when crafted and requirements are met
     *
     * @param ruleItems  - items affected by this rule
     * @param damage     - amount of durability item will lose
     * @param requiredLevel - level required to enable this OCR
     */
    public DurabilityOCR(TagKey<Item> ruleItems, int damage, int requiredLevel) {
        super(ruleItems, requiredLevel);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public void apply(World world, PlayerEntity player, ItemStack stack) {
        if(stack.isDamageable()){
            stack.setDamage(this.getDamage());
        }
    }

    @Override
    public boolean canModifyItemStack() {
        return true;
    }
}
