package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.rules.AbstractCraftingRule;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

abstract class OnCraftRules extends AbstractCraftingRule {

    protected OnCraftRules(@Nullable TagKey<Item> ruleItems){
        super(ruleItems);
    }

    public ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory){
        return craftedItem;
    }

    public ItemStack modifyItemCrafterIsAboutToCraft(ItemStack craftedItem, ServerWorld world, BlockState crafterState, BlockPos crafterPos){
        return craftedItem;
    }

}
