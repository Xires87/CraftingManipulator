package net.fryc.craftingmanipulator.mixin;


import net.fryc.craftingmanipulator.rules.oncraft.ExperienceOCR;
import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.fryc.craftingmanipulator.rules.oncraft.PlaySoundOCR;
import net.fryc.craftingmanipulator.rules.oncraft.StatusEffectOCR;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemStack.class)
abstract class OnCraftMixin {

    Random random = new Random();

    @Inject(method = "onCraft(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;I)V", at = @At("HEAD"))
    private void whenCrafted(World world, PlayerEntity player, int amount, CallbackInfo ci) {
        ItemStack dys = ((ItemStack)(Object)this);
        if(OnCraftRules.getOnCraftRules() != null && !OnCraftRules.getOnCraftRules().isEmpty()){
            for(int i = 0; i < OnCraftRules.getOnCraftRules().size(); i++){
                if(dys.isIn(OnCraftRules.getOnCraftRules().get(i).getRuleItems())){
                    if(OnCraftRules.getOnCraftRules().get(i) instanceof StatusEffectOCR rule){
                        player.addStatusEffect(new StatusEffectInstance(rule.getEffect(), rule.getDuration(), rule.getAmplifier()));
                    }
                    else if(OnCraftRules.getOnCraftRules().get(i) instanceof ExperienceOCR rule){
                        if(rule.isExperience()) player.addExperience(rule.getXp());
                        else player.addExperienceLevels(rule.getXp());
                        if(rule.getXp() > 0) world.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, random.nextFloat(0.4F, 1.0F));
                    }
                    else if(OnCraftRules.getOnCraftRules().get(i) instanceof PlaySoundOCR rule){
                        world.playSound(player, player.getBlockPos(), rule.getSound(), SoundCategory.PLAYERS, rule.getVolume(), rule.getPitch());
                    }
                }
            }
        }
    }

}
