package net.fryc.craftingmanipulator.rules.oncraft;

import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;

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
     * @param pitch - pitch of the sound
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch) {
        super(ruleItems);
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
     * @param pitch - pitch of the sound
     * @param condition     - unlock condition: must be properly paired with tag
     * @param neededItems   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch, UnlockConditions condition, TagKey<?> neededItems) {
        super(ruleItems, condition, neededItems);
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
     * @param pitch - pitch of the sound
     * @param requiredLevel - level required to enable this OCR
     */
    public PlaySoundOCR(TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch, int requiredLevel) {
        super(ruleItems, requiredLevel);
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public SoundEvent getSound() {
        return this.sound;
    }
    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void apply(World world, PlayerEntity player, ItemStack stack) {
        world.playSound(player, player.getBlockPos(), this.getSound(), SoundCategory.PLAYERS, this.getVolume(), this.getPitch());
    }

    @Override
    public boolean canModifyItemStack() {
        return false;
    }
}
