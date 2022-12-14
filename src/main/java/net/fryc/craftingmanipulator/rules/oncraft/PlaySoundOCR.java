package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.registry.tag.TagKey;

public class PlaySoundOCR extends OnCraftRules{

    private final SoundEvent sound;
    private final float volume;
    private final float pitch;

    /**
     * Plays sound when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param sound - sound that will be played
     * @param volume - volume of the sound
     * @param pitch - idk
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch) {
        super("", ruleItems);
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    /**
     * Plays sound when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param sound - sound that will be played
     * @param volume - volume of the sound
     * @param pitch - idk
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch, UnlockConditions condition, TagKey<?> neededItems) {
        super("", ruleItems);
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.condition = condition;
        this.neededItems = neededItems;
    }

    /**
     * Plays sound when player crafts item from tag
     *
     * @param ruleItems - items affected by this rule
     * @param sound - sound that will be played
     * @param volume - volume of the sound
     * @param pitch - idk
     * @param requiredLevel - level required to enable this OCR
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch, int requiredLevel) {
        super("", ruleItems);
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.condition = UnlockConditions.PLAYER_LEVEL;
        this.unlockLevel = requiredLevel;
    }

    public SoundEvent getSound() {
        return sound;
    }
    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }
}
