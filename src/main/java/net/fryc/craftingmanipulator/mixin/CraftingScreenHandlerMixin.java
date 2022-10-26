package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.rules.BeOnBiomeRBR;
import net.fryc.craftingmanipulator.rules.ItemInInventoryRBR;
import net.fryc.craftingmanipulator.rules.RecipeBlockingRules;
import net.fryc.craftingmanipulator.rules.StandNearBlockRBR;
import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CraftingScreenHandler.class)
abstract class CraftingScreenHandlerMixin extends AbstractRecipeScreenHandler<CraftingInventory> {
    public CraftingScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    //blocks recipes when specified conditions are not met
    @ModifyVariable(method = "updateResult(Lnet/minecraft/screen/ScreenHandler;" +
            "Lnet/minecraft/world/World;" +
            "Lnet/minecraft/entity/player/PlayerEntity;" +
            "Lnet/minecraft/inventory/CraftingInventory;" +
            "Lnet/minecraft/inventory/CraftingResultInventory;)V",
            at = @At(value = "STORE", ordinal = 1))
    private static ItemStack blockRecipe(ItemStack stack , ScreenHandler handler, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if(RecipeBlockingRules.getRecipeBlockingRules() != null && !RecipeBlockingRules.getRecipeBlockingRules().isEmpty()){
            for(int i = 0; i < RecipeBlockingRules.getRecipeBlockingRules().size(); i++){
                if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof ItemInInventoryRBR rule){
                    if(stack.isIn(rule.getBlockedItems())){
                        if(!ConditionsHelper.hasCorrectItemInInventory(player, rule.getNeededItems())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                }
                else if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof StandNearBlockRBR rule){
                    if(stack.isIn(rule.getBlockedItems())){
                        if(!ConditionsHelper.standsNearCorrectBlock(player, world, rule.getNeededBlocks())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                }
                else if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof BeOnBiomeRBR rule){
                    if(stack.isIn(rule.getBlockedItems())){
                        if(!ConditionsHelper.isOnCorrectBiome(player, world, rule.getNeededBiomes())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                }
                else {
                    RecipeBlockingRules rule = RecipeBlockingRules.getRecipeBlockingRules().get(i);
                    if(stack.isIn(rule.getBlockedItems())){
                        stack = ItemStack.EMPTY;
                        break;
                    }
                }
            }
        }
        return stack;
    }

}
