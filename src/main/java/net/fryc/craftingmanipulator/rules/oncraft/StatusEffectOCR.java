package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;

public class StatusEffectOCR extends OnCraftRules {

    private final StatusEffect effect;
    private final int duration;
    private final int amplifier;


    /**
     * Gives player status effect when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param effect - status effect that will be applied to player
     * @param duration - duration of status effect
     * @param amplifier - amplifier of status effect
     */
    public StatusEffectOCR(TagKey<Item> ruleItems, StatusEffect effect, int duration, int amplifier) {
        super(ruleItems);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    /**
     * Gives player status effect when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param effect - status effect that will be applied to player
     * @param duration - duration of status effect
     * @param amplifier - amplifier of status effect
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public StatusEffectOCR(TagKey<Item> ruleItems, StatusEffect effect, int duration, int amplifier, UnlockConditions condition, TagKey<?> neededItems) {
        super(ruleItems, condition, neededItems);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    /**
     * Gives player status effect when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param effect - status effect that will be applied to player
     * @param duration - duration of status effect
     * @param amplifier - amplifier of status effect
     * @param requiredLevel - level required to enable this OCR
     */
    public StatusEffectOCR(TagKey<Item> ruleItems, StatusEffect effect, int duration, int amplifier, int requiredLevel) {
        super(ruleItems, requiredLevel);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }


    public StatusEffect getEffect() {
        return this.effect;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getAmplifier() {
        return this.amplifier;
    }


}
