package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.rules.oncraft.ExperienceOCR;
import net.fryc.craftingmanipulator.rules.recipeblocking.StandNearBlockRBR;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Identifier CM_DRAWINGS_TEXTURE = new Identifier(MOD_ID, "textures/gui/crafting_table_modified.png");
	@Override
	public void onInitialize() {
		CMRegistries.registerDrawing("crafting_red_x", new Drawing(
				CM_DRAWINGS_TEXTURE, 87, 32, 0, 0, 28, 21
		));

		StandNearBlockRBR test = (StandNearBlockRBR) CMRegistries.registerCraftingRule("test_stand", new StandNearBlockRBR(null, null));
		test.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);
		test.getOrCreateUnlockThings().add(Blocks.COBBLESTONE);

		ExperienceOCR ocr = (ExperienceOCR) CMRegistries.registerCraftingRule("test_exp", new ExperienceOCR(null, 2, true));
		ocr.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);

	}
}
