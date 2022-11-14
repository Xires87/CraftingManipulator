package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.rules.*;
import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.rules.oncraft.AttributeModifierOCR;
import net.fryc.craftingmanipulator.rules.oncraft.DurabilityOCR;
import net.fryc.craftingmanipulator.rules.oncraft.OnCraftRules;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.UUID;

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
        //getting RBR
        if(RecipeBlockingRules.getRecipeBlockingRules() != null && !RecipeBlockingRules.getRecipeBlockingRules().isEmpty()){
            for(int i = 0; i < RecipeBlockingRules.getRecipeBlockingRules().size(); i++){
                if(stack.isIn(RecipeBlockingRules.getRecipeBlockingRules().get(i).getBlockedItems())){
                    //ItemInInventoryRBR
                    if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof ItemInInventoryRBR rule){
                        if((!ConditionsHelper.hasCorrectItemInInventory(player, rule.getNeededItems()) && !rule.isReversed()) ||
                                (ConditionsHelper.hasCorrectItemInInventory(player, rule.getNeededItems()) && rule.isReversed())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                    //StandNearBlockRBR
                    else if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof StandNearBlockRBR rule){
                        if((!ConditionsHelper.standsNearCorrectBlock(player, world, rule.getNeededBlocks()) && !rule.isReversed()) ||
                                (ConditionsHelper.standsNearCorrectBlock(player, world, rule.getNeededBlocks()) && rule.isReversed())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                    //BeOnBiomeRBR
                    else if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof BeOnBiomeRBR rule){
                        if((!ConditionsHelper.isOnCorrectBiome(player, world, rule.getNeededBiomes()) && !rule.isReversed()) ||
                                (ConditionsHelper.isOnCorrectBiome(player, world, rule.getNeededBiomes()) && rule.isReversed())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                    //PlayerLevelRBR
                    else if(RecipeBlockingRules.getRecipeBlockingRules().get(i) instanceof PlayerLevelRBR rule){
                        if((!ConditionsHelper.playerHasLevel(player, rule.getPlayerLevel()) && !rule.isReversed()) ||
                                (ConditionsHelper.playerHasLevel(player, rule.getPlayerLevel()) && rule.isReversed())){
                            stack = ItemStack.EMPTY;
                            break;
                        }
                    }
                    //RecipeBlockingRules
                    else {
                        stack = ItemStack.EMPTY;
                        break;
                    }
                }
            }
        }

        //getting OCR
        if(stack != ItemStack.EMPTY){
            if(OnCraftRules.getOnCraftRules() != null && !OnCraftRules.getOnCraftRules().isEmpty()){
                for(int i = 0; i < OnCraftRules.getOnCraftRules().size(); i++){
                    if(stack.isIn(OnCraftRules.getOnCraftRules().get(i).getRuleItems())){
                        //DurabilityOCR
                        if(OnCraftRules.getOnCraftRules().get(i) instanceof DurabilityOCR rule){
                            if(stack.isDamageable()){
                                if((ConditionsHelper.detectAndUnlock(rule.getCondition(), player, rule.getUnlockItems(), rule.getUnlockLevel()) && !rule.isReversed()) ||
                                        (!ConditionsHelper.detectAndUnlock(rule.getCondition(), player, rule.getUnlockItems(), rule.getUnlockLevel()) && rule.isReversed())){
                                    stack.setDamage(rule.getDurability());
                                }
                            }
                        }
                        //AttributeModifierOCR
                        else if(OnCraftRules.getOnCraftRules().get(i) instanceof AttributeModifierOCR rule){
                            if(stack.getItem() instanceof ArmorItem item){
                                if((ConditionsHelper.detectAndUnlock(rule.getCondition(), player, rule.getUnlockItems(), rule.getUnlockLevel()) && !rule.isReversed()) ||
                                        (!ConditionsHelper.detectAndUnlock(rule.getCondition(), player, rule.getUnlockItems(), rule.getUnlockLevel()) && rule.isReversed())){
                                    stack = setArmorProtection(stack, item);
                                    stack.addAttributeModifier(rule.getAttribute(), new EntityAttributeModifier("Custom modifiers", rule.getValue(), rule.getOperation()), item.getSlotType());
                                }
                            }
                        }
                    }
                }
            }
        }

        return stack;
    }

    private static ItemStack setArmorProtection(ItemStack stack, ArmorItem item){
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2960207a"),"Armor modifier", item.getMaterial().getProtectionAmount(item.getSlotType()), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2950207a"),"Armor toughness", item.getMaterial().getToughness(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        stack.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(UUID.fromString("1943f7d5-061e-43d4-b1d5-8bea2940207a"),"Armor knockback resistance", item.getMaterial().getKnockbackResistance(), EntityAttributeModifier.Operation.ADDITION), item.getSlotType());
        return stack;
    }

}
