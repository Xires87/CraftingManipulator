package net.fryc.craftingmanipulator.mixin;


import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.rules.CraftingRule;
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
    private void whenCrafted(World world, PlayerEntity player, int amount, CallbackInfo info) {
        ItemStack dys = ((ItemStack)(Object)this); // runs on client and server
        for(CraftingRule rule : CMRegistries.CRAFTING_RULES.values()){
            if(!rule.isEnabled() || !rule.isInAppriopriateScreenHandler(player.currentScreenHandler)) continue;
            rule.onTakeOutput(dys, amount, player, world);
        }
    }

}
