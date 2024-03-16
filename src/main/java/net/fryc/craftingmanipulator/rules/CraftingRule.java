package net.fryc.craftingmanipulator.rules;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public interface CraftingRule {

    ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory);
    int modifyAmount(ItemStack craftedItem, int amount, PlayerEntity player, World world);

    default boolean isEnabled(){
        return true;
    }

    default boolean isInAppriopriateScreenHandler(ScreenHandler handler){
        return handler.getClass() == CraftingScreenHandler.class || handler.getClass() == PlayerScreenHandler.class;
    }

}
