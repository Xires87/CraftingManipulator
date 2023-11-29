package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.fryc.craftingmanipulator.rules.oncraft.DurabilityOCR;
import net.fryc.craftingmanipulator.rules.oncraft.ExperienceOCR;
import net.fryc.craftingmanipulator.rules.oncraft.PlaySoundOCR;
import net.fryc.craftingmanipulator.rules.oncraft.StatusEffectOCR;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;


public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		/*
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

		 */

		HashSet<RegistryKey<Biome>> secik = new HashSet<>();
		secik.add(BiomeKeys.SWAMP);
		ExperienceOCR exp = new ExperienceOCR(ItemTags.PLANKS, 2, true, UnlockConditions.ON_BIOME, null);
		exp.setAdditionalNeededThings(secik);

		StatusEffectOCR status = new StatusEffectOCR(ItemTags.PLANKS, StatusEffects.INSTANT_DAMAGE, 1, 0,6);
		status.getAdditionalAffectedItems().add(Items.WOODEN_AXE);
		status.setReversed(true);

		PlaySoundOCR sound = new PlaySoundOCR(null, SoundEvents.ENTITY_PLAYER_HURT_FREEZE, 1.0F, 1.0F);
		sound.setAdditionalAffectedItems(status.getAdditionalAffectedItems());

		HashSet<Block> blokSet = new HashSet<>();
		blokSet.add(Blocks.COBBLESTONE);
		blokSet.add(Blocks.ACACIA_LOG);
		DurabilityOCR durability = new DurabilityOCR(ItemTags.PIGLIN_LOVED, 17, UnlockConditions.BLOCK_NEARBY, null);
		durability.setAdditionalNeededThings(blokSet);
		durability.setReversed(true);
	}
}
