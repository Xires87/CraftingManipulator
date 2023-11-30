package net.fryc.craftingmanipulator;

import net.fabricmc.api.ClientModInitializer;
import net.fryc.craftingmanipulator.conditions.PressedKey;
import net.fryc.craftingmanipulator.rules.tooltips.TooltipRules;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CraftingManipulatorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // todo przetestowac jeszcze raz wszystkie reguly i potem przetestowac na Hammers and Smithing
        TooltipRules rule = new TooltipRules(ItemTags.PIGLIN_LOVED, Text.literal(""), PressedKey.SHIFT, Text.literal("podwojny jak sie klika a tak pojedynczy").formatted(Formatting.DARK_PURPLE));
        TooltipRules fgfsd = new TooltipRules(ItemTags.PIGLIN_LOVED, Text.literal("taki se tutaj tooltip"), PressedKey.SHIFT, Text.literal("wow! wcisnales shifta!!").formatted(Formatting.DARK_PURPLE));
        rule.getAdditionalAffectedItems().add(Items.OAK_PLANKS);
    }
}
