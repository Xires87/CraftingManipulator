package net.fryc.craftingmanipulator.rules.oncraft;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

public class StatusEffectOCR extends OnCraftRules {

    private final StatusEffect effect;
    private final int duration;
    private final int amplifier;


    /**
     * Gives player status effect when player crafts item from tag
     *
     * @param tooltip   - tooltip that will be added to items specified in rule
     * @param ruleItems - items affected by this rule
     * @param effect - status effect that will be applied to player
     * @param duration - duration of status effect
     * @param amplifier - amplifier of status effect
     */
    public StatusEffectOCR(String tooltip, TagKey<Item> ruleItems, StatusEffect effect, int duration, int amplifier) {
        super(tooltip, ruleItems);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
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
