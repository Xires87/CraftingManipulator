package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.rules.oncraft.ExperienceOCR;
import net.fryc.craftingmanipulator.rules.recipeblocking.StandNearBlockRBR;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Identifier CM_DRAWINGS_CRAFTING_TEXTURE = new Identifier(MOD_ID, "textures/gui/crafting_table_modified.png");
	private static final Identifier CM_DRAWINGS_INVENTORY_TEXTURE = new Identifier(MOD_ID, "textures/gui/inventory_modified.png");
	@Override
	public void onInitialize() {
		CMRegistries.registerDrawing("crafting_red_x", new Drawing(
				CraftingScreenHandler.class, CM_DRAWINGS_CRAFTING_TEXTURE, 87, 32, 0, 0, 28, 21
		));
		CMRegistries.registerDrawing("inventory_red_x", new Drawing(
				PlayerScreenHandler.class, CM_DRAWINGS_INVENTORY_TEXTURE, 134, 28, 2, 50, 18, 15
		));
		// todo dodac 2 rysunki levela

		// todo usunac ponizsze jak skoncze testy
		StandNearBlockRBR test = (StandNearBlockRBR) CMRegistries.registerCraftingRule("test_stand", new StandNearBlockRBR());
		test.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);
		test.getOrCreateUnlockThings().add(Blocks.COBBLESTONE);

		ExperienceOCR ocr = (ExperienceOCR) CMRegistries.registerCraftingRule("test_exp", new ExperienceOCR(2, true));
		ocr.getOrCreateAdditionalAffectedItems().add(Items.OAK_PLANKS);

	}
}
