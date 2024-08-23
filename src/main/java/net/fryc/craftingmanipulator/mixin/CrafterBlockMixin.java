package net.fryc.craftingmanipulator.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.rules.CraftingRule;
import net.minecraft.block.BlockState;
import net.minecraft.block.CrafterBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrafterBlock.class)
abstract class CrafterBlockMixin {

    @WrapOperation(
            method = "craft(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipe;craft(" +
                    "Lnet/minecraft/recipe/input/RecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;" +
                    ")Lnet/minecraft/item/ItemStack;")
    )
    private ItemStack modifyItemCrafterIsAboutToCraft(CraftingRecipe craftingRecipe, RecipeInput craftingRecipeInput,
                                                      RegistryWrapper.WrapperLookup registryManager, Operation<ItemStack> original,
                                                      BlockState state, ServerWorld world, BlockPos pos) {

        ItemStack stack = original.call(craftingRecipe, craftingRecipeInput, registryManager);
        for(CraftingRule rule : CMRegistries.CRAFTING_RULES.values()){
            if(!rule.isEnabled()) continue;
            stack = rule.modifyItemCrafterIsAboutToCraft(stack, world, state, pos);
        }
        return stack;
    }
}
