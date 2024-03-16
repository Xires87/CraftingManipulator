package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.rules.AbstractCraftingRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public abstract class OnCraftRules extends AbstractCraftingRule {

    protected OnCraftRules(TagKey<Item> ruleItems){
        super(ruleItems);
    }

    public ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory){
        return craftedItem;
    }

}
