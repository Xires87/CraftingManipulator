package net.fryc.craftingmanipulator.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fryc.craftingmanipulator.network.payloads.ResetDrawingPayload;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.rules.CraftingRule;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
abstract class CraftingScreenHandlerMixin implements DrawsSelectedTextures, DrawsSelectedTooltips {

    @Shadow
    private @Final PlayerEntity player;


    // runs only on server
    @WrapOperation(
            method = "updateResult(Lnet/minecraft/screen/ScreenHandler;" +
                    "Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/entity/player/PlayerEntity;" +
                    "Lnet/minecraft/inventory/RecipeInputInventory;" +
                    "Lnet/minecraft/inventory/CraftingResultInventory;" +
                    "Lnet/minecraft/recipe/RecipeEntry;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipe;craft(" +
                    "Lnet/minecraft/recipe/input/RecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;" +
                    ")Lnet/minecraft/item/ItemStack;")
    )
    private static ItemStack modifyItemFromRecipe(
            CraftingRecipe craftingRecipe, RecipeInput craftingRecipeInput,
            RegistryWrapper.WrapperLookup registryManager, Operation<ItemStack> original,
            ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory,
            CraftingResultInventory resultInventory, @Nullable RecipeEntry<CraftingRecipe> recipe
    ) {
        ItemStack stack = original.call(craftingRecipe, craftingRecipeInput, registryManager);
        for(CraftingRule rule : CMRegistries.CRAFTING_RULES.values()){
            if(!rule.isEnabled() || !rule.isInAppriopriateScreenHandler(handler)) continue;
            stack = rule.modifyCraftedItem(stack, (ServerPlayerEntity) player, (ServerWorld) world, handler, craftingInventory, resultInventory);
        }
        return stack;
    }

    @Inject(method = "onContentChanged(Lnet/minecraft/inventory/Inventory;)V", at = @At("HEAD"))
    public void resetDrawings(Inventory inventory, CallbackInfo info) {
        if(!this.player.getWorld().isClient()){
            ServerPlayNetworking.send(((ServerPlayerEntity) this.player), new ResetDrawingPayload(true));
        }
    }

}
