package net.fryc.craftingmanipulator.mixin;


import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
abstract class OnCraftMixin {

    @Inject(method = "onCraft(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;I)V", at = @At("HEAD"))
    private void whenCrafted(World world, PlayerEntity player, int amount, CallbackInfo ci) {
        ItemStack dys = ((ItemStack)(Object)this);
        if(OnCraftRules.getOnCraftRules() != null && !OnCraftRules.getOnCraftRules().isEmpty()){
            for(OnCraftRules oRule : OnCraftRules.getOnCraftRules()){
                if(oRule.canModifyItemStack()) continue;
                boolean isTag = false;
                if(oRule.getAffectedItems() != null){
                    isTag = dys.isIn(oRule.getAffectedItems());
                    if(!isTag && oRule.areAdditionalAffectedItemsNull()) continue;
                }
                if(!oRule.areAdditionalAffectedItemsNull() && !isTag){
                    if(!oRule.getAdditionalAffectedItems().contains(dys.getItem())) continue;
                }

                if(oRule.conditionsAreMet(player)){
                    oRule.apply(world, player, dys);
                }
            }
        }
    }

}
