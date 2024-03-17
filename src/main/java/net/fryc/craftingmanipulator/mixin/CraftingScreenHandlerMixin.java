package net.fryc.craftingmanipulator.mixin;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fryc.craftingmanipulator.network.ModPackets;
import net.fryc.craftingmanipulator.rules.CraftingRule;
import net.fryc.craftingmanipulator.rules.RulesHolders;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
abstract class CraftingScreenHandlerMixin implements DrawsSelectedTextures {

    private boolean isItemStackModified = false;

    @Shadow
    private @Final PlayerEntity player;

    @Redirect(method = "updateResult(Lnet/minecraft/screen/ScreenHandler;" +
            "Lnet/minecraft/world/World;" +
            "Lnet/minecraft/entity/player/PlayerEntity;" +
            "Lnet/minecraft/inventory/RecipeInputInventory;" +
            "Lnet/minecraft/inventory/CraftingResultInventory;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipe;craft(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack modifyItemFromRecipe(CraftingRecipe recipe, Inventory inventory, DynamicRegistryManager manager , ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory) {
        ItemStack stack = recipe.craft(craftingInventory, world.getRegistryManager()); // runs only on server
        for(CraftingRule rule : RulesHolders.CRAFTING_RULES.values()){
            if(!rule.isEnabled() || !rule.isInAppriopriateScreenHandler(handler)) continue;
            stack = rule.modifyCraftedItem(stack, player, world, handler, craftingInventory, resultInventory);
        }
        return stack;
    }

    @Inject(method = "onContentChanged(Lnet/minecraft/inventory/Inventory;)V", at = @At("HEAD"))
    public void resetDrawings(Inventory inventory, CallbackInfo info) {
        if(!this.player.getWorld().isClient()){
            ServerPlayNetworking.send(((ServerPlayerEntity) this.player), ModPackets.RESET_DRAWING, PacketByteBufs.empty());
        }
    }


    public boolean hasEnabledDrawing() {
        return this.isItemStackModified;
    }

    public boolean isItemModified() {
        return this.isItemStackModified;
    }
    public void setItemIsModified(boolean modified) {
        this.isItemStackModified = modified;
    }

    public void informAboutItemModification() {
        this.isItemStackModified = true;
    }
}
