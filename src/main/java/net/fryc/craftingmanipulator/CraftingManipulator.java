package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.network.ModPackets;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
import net.fryc.craftingmanipulator.rules.oncraft.ExperienceOCR;
import net.fryc.craftingmanipulator.rules.recipeblocking.ItemInInventoryRBR;
import net.fryc.craftingmanipulator.rules.recipeblocking.PlayerLevelRBR;
import net.fryc.craftingmanipulator.rules.recipeblocking.StandNearBlockRBR;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
*/

public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Identifier CM_DRAWINGS_CRAFTING_TEXTURE = Identifier.of(MOD_ID, "textures/gui/crafting_table_modified.png");
	private static final Identifier CM_DRAWINGS_INVENTORY_TEXTURE = Identifier.of(MOD_ID, "textures/gui/inventory_modified.png");
	@Override
	public void onInitialize() {
		CMRegistries.registerDrawing("crafting_red_x", new Drawing(
				CraftingScreenHandler.class, CM_DRAWINGS_CRAFTING_TEXTURE, 87, 32, 0, 0, 28, 21
		));
		CMRegistries.registerDrawing("inventory_red_x", new Drawing(
				PlayerScreenHandler.class, CM_DRAWINGS_INVENTORY_TEXTURE, 134, 28, 2, 50, 18, 15
		));

		CMRegistries.registerDrawing("crafting_xp_orb", new Drawing(
				CraftingScreenHandler.class, CM_DRAWINGS_CRAFTING_TEXTURE, 95, 21, 0, 22, 11, 11
		));
		CMRegistries.registerDrawing("inventory_xp_orb", new Drawing(
				PlayerScreenHandler.class, CM_DRAWINGS_INVENTORY_TEXTURE, 137, 16, 2, 38, 11, 11
		));

		ModPackets.registerPayloads();

		/*
		StandNearBlockRBR test = (StandNearBlockRBR) CMRegistries.registerCraftingRule("test_stand", new StandNearBlockRBR(ItemTags.BUTTONS, BlockTags.BAMBOO_BLOCKS));
		test.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);
		test.getOrCreateUnlockThings().add(Blocks.COBBLESTONE);

		ExperienceOCR ocr = (ExperienceOCR) CMRegistries.registerCraftingRule("test_exp", new ExperienceOCR(-2, true));
		ocr.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);

		PlayerLevelRBR testLevel = (PlayerLevelRBR) CMRegistries.registerCraftingRule("test_level", new PlayerLevelRBR(ItemTags.PIGLIN_LOVED, 5));
		ItemInInventoryRBR itemInTest = (ItemInInventoryRBR) CMRegistries.registerCraftingRule("test_item", new ItemInInventoryRBR(ItemTags.BUTTONS, ItemTags.BOATS));
		itemInTest.setReversed(true);
		*/
	}
}
