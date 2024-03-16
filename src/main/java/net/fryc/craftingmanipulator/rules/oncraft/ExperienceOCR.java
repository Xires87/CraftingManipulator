package net.fryc.craftingmanipulator.rules.oncraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Random;

public class ExperienceOCR extends OnCraftRules{

    private final int xp;
    private final boolean isExperience;
    private final Random random = new Random();



    /**
     * Gives player experience (or levels) after crafting spedified items
     *
     * @param ruleItems    - items affected by this rule
     * @param amount       - amount of experience (or levels) player will get
     * @param isExperience - when false, player will get levels instead of experience
     */
    public ExperienceOCR(TagKey<Item> ruleItems, int amount, boolean isExperience) {
        super(ruleItems);
        this.xp = amount;
        this.isExperience = isExperience;
    }


    public int getXp() {
        return this.xp;
    }

    public boolean isExperience() {
        return this.isExperience;
    }

    @Override
    public int modifyAmount(ItemStack craftedItem, int amount, PlayerEntity player, World world) {
        if(this.isItemAffectedByThisRule(craftedItem)){
            if(this.isExperience()){
                player.addExperience(this.getXp());
            }
            else {
                player.addExperienceLevels(this.getXp());
            }
            if(this.getXp() > 0) world.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, this.random.nextFloat(0.4F, 1.0F));
        }
        return amount;
    }
}
