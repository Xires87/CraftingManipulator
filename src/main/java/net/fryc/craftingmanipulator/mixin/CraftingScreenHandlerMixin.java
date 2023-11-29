package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.fryc.craftingmanipulator.rules.recipeblocking.RecipeBlockingRules;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftingScreenHandler.class)
abstract class CraftingScreenHandlerMixin {

    //blocks recipes when specified conditions are not met
    @Redirect(method = "updateResult(Lnet/minecraft/screen/ScreenHandler;" +
            "Lnet/minecraft/world/World;" +
            "Lnet/minecraft/entity/player/PlayerEntity;" +
            "Lnet/minecraft/inventory/RecipeInputInventory;" +
            "Lnet/minecraft/inventory/CraftingResultInventory;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipe;craft(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack blockRecipe(CraftingRecipe recipe, Inventory inventory, DynamicRegistryManager manager , ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        ItemStack stack = recipe.craft(craftingInventory, world.getRegistryManager());
        //getting RBR
        if(RecipeBlockingRules.getRecipeBlockingRules() != null && !RecipeBlockingRules.getRecipeBlockingRules().isEmpty()){
            for(RecipeBlockingRules bRule : RecipeBlockingRules.getRecipeBlockingRules()){
                boolean isTag = false;
                if(bRule.getAffectedItems() != null){
                    isTag = stack.isIn(bRule.getAffectedItems());
                    if(!isTag && bRule.areAdditionalAffectedItemsNull()) continue;
                }
                if(!bRule.areAdditionalAffectedItemsNull() && !isTag){
                    if(!bRule.getAdditionalAffectedItems().contains(stack.getItem())) continue;
                }

                if(!bRule.conditionsAreMet(player)){
                    stack = ItemStack.EMPTY;
                    break;
                }
            }
        }

        //getting OCR that can modify items
        if(stack != ItemStack.EMPTY){
            if(OnCraftRules.getOnCraftRules() != null && !OnCraftRules.getOnCraftRules().isEmpty()){
                for(OnCraftRules oRule : OnCraftRules.getOnCraftRules()){
                    if(!oRule.canModifyItemStack()) continue;
                    boolean isTag = false;
                    if(oRule.getAffectedItems() != null){
                        isTag = stack.isIn(oRule.getAffectedItems());
                        if(!isTag && oRule.areAdditionalAffectedItemsNull()) continue;
                    }
                    if(!oRule.areAdditionalAffectedItemsNull() && !isTag){
                        if(!oRule.getAdditionalAffectedItems().contains(stack.getItem())) continue;
                    }

                    if(oRule.conditionsAreMet(player)){
                        oRule.apply(world, player, stack);
                    }
                }
            }
        }

        return stack;
    }

}
