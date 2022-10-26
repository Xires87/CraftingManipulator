package net.fryc.craftingmanipulator.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fryc.craftingmanipulator.rules.RecipeBlockingRules;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
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
        List<RecipeBlockingRules> list = RecipeBlockingRules.getRecipeBlockingRules();
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
}
