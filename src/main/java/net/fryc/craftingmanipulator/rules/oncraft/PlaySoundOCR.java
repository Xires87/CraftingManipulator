package net.fryc.craftingmanipulator.rules.oncraft;

import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.TagKey;

public class PlaySoundOCR extends OnCraftRules{

    private final SoundEvent sound;
    private final float volume;
    private final float pitch;

    /**
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
