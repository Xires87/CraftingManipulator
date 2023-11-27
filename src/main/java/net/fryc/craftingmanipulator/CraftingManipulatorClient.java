package net.fryc.craftingmanipulator;

import net.fabricmc.api.ClientModInitializer;
import net.fryc.craftingmanipulator.rules.tooltips.TooltipRules;
import net.minecraft.registry.tag.ItemTags;

public class CraftingManipulatorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TooltipRules rule = new TooltipRules(ItemTags.PIGLIN_LOVED, "testujemy");
    }
}
