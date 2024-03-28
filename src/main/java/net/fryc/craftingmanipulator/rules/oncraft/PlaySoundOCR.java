package net.fryc.craftingmanipulator.rules.oncraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlaySoundOCR extends OnCraftRules{

    private final SoundEvent sound;
    private final float volume;
    private final float pitch;

    /**
     * Plays sound when player crafts item specified in tag or HashSet
     *
     * @param ruleItems - items affected by this rule
     * @param sound - sound that will be played
     * @param volume - volume of the sound
     * @param pitch - pitch of the sound
     */
    public PlaySoundOCR(@Nullable TagKey<Item> ruleItems, SoundEvent sound, float volume, float pitch) {
        super(ruleItems);
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    /**
     * Plays sound when player crafts item specified in HashSet
     *
     * @param sound - sound that will be played
     * @param volume - volume of the sound
     * @param pitch - pitch of the sound
     */
    public PlaySoundOCR(SoundEvent sound, float volume, float pitch) {
        this(null, sound, volume, pitch);
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
    public void onTakeOutput(ItemStack craftedItem, int amount, PlayerEntity player, World world) {
        if(!world.isClient()){
            if(this.isItemAffectedByThisRule(craftedItem)){
                world.playSound(null, player.getBlockPos(), this.getSound(), SoundCategory.PLAYERS, this.getVolume(), this.getPitch());
            }
        }
    }
}
