package net.fryc.craftingmanipulator;

import net.fabricmc.api.ModInitializer;
import net.fryc.craftingmanipulator.rules.oncraft.PlaySoundOCR;
import net.fryc.craftingmanipulator.rules.oncraft.StatusEffectOCR;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraftingManipulator implements ModInitializer {
	public static final String MOD_ID = "craftingmanipulator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
StatusEffectOCR ocr = new StatusEffectOCR("Teleport test", ItemTags.PLANKS, StatusEffects.SLOW_FALLING, 200, 0);
PlaySoundOCR sound = new PlaySoundOCR(ItemTags.PLANKS, SoundEvents.BLOCK_WOOD_BREAK, 1.0F, 1.0F);
	@Override
	public void onInitialize() {

	}
}
