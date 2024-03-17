package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.rules.RulesHolders;
import net.fryc.craftingmanipulator.rules.recipeblocking.StandNearBlockRBR;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		StandNearBlockRBR test = (StandNearBlockRBR) RulesHolders.registerCraftingRule("test_stand", new StandNearBlockRBR(null, null));
		test.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);
		test.getOrCreateUnlockThings().add(Blocks.COBBLESTONE);

	}
}
