package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

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
        super(ruleItems);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.condition = condition;
        this.neededItems = neededItems;
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
        super(ruleItems);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.condition = UnlockConditions.PLAYER_LEVEL;
        this.unlockLevel = requiredLevel;
    }


    public StatusEffect getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }
}
