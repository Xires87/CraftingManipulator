package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.rules.oncraft.AttributeModifierOCR;
import net.fryc.craftingmanipulator.rules.oncraft.DurabilityOCR;
import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.fryc.craftingmanipulator.rules.recipeblocking.RecipeBlockingRules;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(CraftingScreenHandler.class)
abstract class CraftingScreenHandlerMixin extends AbstractRecipeScreenHandler<CraftingInventory> {
    public CraftingScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

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

        //getting OCR
        if(stack != ItemStack.EMPTY){
            if(OnCraftRules.getOnCraftRules() != null && !OnCraftRules.getOnCraftRules().isEmpty()){
                for(OnCraftRules oRule : OnCraftRules.getOnCraftRules()){
                    if(!(oRule instanceof DurabilityOCR || oRule instanceof AttributeModifierOCR)) continue;
                    boolean isTag;
                    if(stack.isIn(oRule.getAffectedItems())){
                        isTag = true;
                    }
                    else if(oRule.getAdditionalAffectedItems().contains(stack.getItem())){
                        isTag = false;
                    }
                    else continue;

                    if(oRule.conditionsAreMet(player)){ // todo sprawdzanie wartosci z setow i testy
                        if(oRule instanceof DurabilityOCR rule){
                            if(stack.isDamageable()){
                                stack.setDamage(rule.getDurability());
                            }
                        }
                        else if(oRule instanceof AttributeModifierOCR rule){
                            if(stack.getItem() instanceof ArmorItem item){
                                setArmorProtection(stack, item);
                                stack.addAttributeModifier(rule.getAttribute(), new EntityAttributeModifier("Custom modifiers", rule.getValue(), rule.getOperation()), item.getSlotType());
                            }
                        }
                    }
                }
            }
        }

        return stack;
    }

    private static void setArmorProtection(ItemStack stack, ArmorItem item){
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2960207a"),"Armor modifier", item.getMaterial().getProtection(item.getType()), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2950207a"),"Armor toughness", item.getMaterial().getToughness(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2940207a"),"Armor knockback resistance", item.getMaterial().getKnockbackResistance(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
    }

}
