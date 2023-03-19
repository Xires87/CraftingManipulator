package net.fryc.craftingmanipulator.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fryc.craftingmanipulator.rules.tooltips.TooltipRules;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
abstract class ItemTooltipMixin implements ItemConvertible, FabricItem {


    @Inject(at = @At("HEAD"), method = "appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Lnet/minecraft/client/item/TooltipContext;)V")
    private void tooltipsForBlockedItems(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context,CallbackInfo info) {
        Item dys = ((Item)(Object)this);

        List<TooltipRules> tooltips = TooltipRules.getTooltipRules();
        if(!tooltips.isEmpty()){
            for(TooltipRules rule : tooltips){
                if(dys.getDefaultStack().isIn(rule.getAffectedItems())){
                    if(rule.isPressingSelectedKey() && !rule.getTooltipWhenKeyPressed().isEmpty()){
                        if(rule.forceAddingTooltip || !tooltip.contains(Text.literal(rule.getTooltipWhenKeyPressed()))){
                            tooltip.add(Text.literal(rule.getTooltipWhenKeyPressed()).formatted(rule.tooltipWhenKeyPressedFormatting));
                        }
                    }
                    else if(!rule.getTooltip().isEmpty()){
                        if(rule.forceAddingTooltip || !tooltip.contains(Text.literal(rule.getTooltip()))){
                            tooltip.add(Text.literal(rule.getTooltip()).formatted(rule.tooltipFormatting));
                        }
                    }
                }
            }
        }

    }
}
