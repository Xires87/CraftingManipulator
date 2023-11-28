package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.rules.recipeblocking.BeOnBiomeRBR;
import net.fryc.craftingmanipulator.rules.recipeblocking.ItemInInventoryRBR;
import net.fryc.craftingmanipulator.rules.recipeblocking.PlayerLevelRBR;
import net.fryc.craftingmanipulator.rules.recipeblocking.StandNearBlockRBR;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		StandNearBlockRBR blok = new StandNearBlockRBR(ItemTags.PLANKS, null);
		blok.getAdditionalNeededThings().add(Blocks.COBBLESTONE);
		blok.getAdditionalNeededThings().add(Blocks.DIAMOND_ORE);
		blok.getAdditionalAffectedItems().add(Items.GOLDEN_CHESTPLATE);
		blok.getAdditionalAffectedItems().add(Items.GOLDEN_LEGGINGS);

		PlayerLevelRBR level = new PlayerLevelRBR(ItemTags.PLANKS, 6);
		level.setReversed(true);
		level.setAdditionalAffectedItems(blok.getAdditionalAffectedItems());

		BeOnBiomeRBR biome = new BeOnBiomeRBR(null, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS);
		biome.getAdditionalNeededThings().add(BiomeKeys.RIVER);
		biome.getAdditionalAffectedItems().add(Items.WOODEN_AXE);

		ItemInInventoryRBR itemin = new ItemInInventoryRBR(ItemTags.BUTTONS, null);
		itemin.setAdditionalAffectedItems(biome.getAdditionalAffectedItems());
		itemin.getAdditionalNeededThings().add(Items.CACTUS);
	}
}
