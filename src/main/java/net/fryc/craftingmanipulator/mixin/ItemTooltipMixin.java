package net.fryc.craftingmanipulator.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fryc.craftingmanipulator.rules.RecipeBlockingRules;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.fryc.craftingmanipulator.rules.tooltips.TooltipRules;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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

        // code between this and next comment will soon be deleted
        List<RecipeBlockingRules> list = RecipeBlockingRules.getRecipeBlockingRules();
        List<OnCraftRules> seclist = OnCraftRules.getOnCraftRules();
        if(!list.isEmpty()){
            for (RecipeBlockingRules rule : list) {
                if (dys.getDefaultStack().isIn(rule.getBlockedItems())){
                    if (rule.getUnlockCondition() != UnlockConditions.NONE){
                        if(!rule.getRuleName().isEmpty()){
                            if(Screen.hasShiftDown()){
                                tooltip.add(Text.literal(rule.getRuleName()).formatted(Formatting.DARK_RED));
                            }
                            else {
                                if(!tooltip.contains(Text.literal("Crafting requirements (SHIFT)").formatted(Formatting.YELLOW))){
                                    tooltip.add(Text.literal("Crafting requirements (SHIFT)").formatted(Formatting.YELLOW));
                                }
                            }
                        }
                    }
                    else {
                        if(!tooltip.contains(Text.literal("Uncraftable").formatted(Formatting.RED, Formatting.UNDERLINE, Formatting.BOLD))){
                            tooltip.add(Text.literal("Uncraftable").formatted(Formatting.RED, Formatting.UNDERLINE, Formatting.BOLD));
                        }
                        if(!rule.getRuleName().isEmpty() && !rule.getRuleName().equals("Uncraftable")){
                            if(Screen.hasShiftDown()) tooltip.add(Text.literal(rule.getRuleName()).formatted(Formatting.DARK_RED, Formatting.UNDERLINE));
                        }
                    }
                }
            }
        }
        if(!seclist.isEmpty()){
            for (OnCraftRules rule : seclist) {
                if(!rule.getRuleName().isEmpty()){
                    if(dys.getDefaultStack().isIn(rule.getRuleItems())){
                        if(Screen.hasControlDown()){
                            tooltip.add(Text.literal(rule.getRuleName()).formatted(Formatting.DARK_PURPLE));
                        }
                        else {
                            if(!tooltip.contains(Text.literal("When crafted: (CTRL)").formatted(Formatting.GOLD))){
                                tooltip.add(Text.literal("When crafted: (CTRL)").formatted(Formatting.GOLD));
                            }
                        }
                    }
                }
            }
        }
        // ^^^ upper code will be deleted ^^^

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
