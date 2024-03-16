package net.fryc.craftingmanipulator.mixin;


import net.fryc.craftingmanipulator.rules.CraftingRule;
import net.fryc.craftingmanipulator.rules.RulesHolders;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
abstract class OnCraftMixin {

    @ModifyVariable(method = "onCraft(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;I)V", at = @At("HEAD"))
    private int whenCrafted(World world, PlayerEntity player, int amount) {
        ItemStack dys = ((ItemStack)(Object)this);
        for(CraftingRule rule : RulesHolders.CRAFTING_RULES.values()){
            if(!rule.isEnabled() || !rule.isInAppriopriateScreenHandler(player.currentScreenHandler)) continue;
            amount = rule.modifyAmount(dys, amount, player, world);
        }
        return amount;
    }

}
