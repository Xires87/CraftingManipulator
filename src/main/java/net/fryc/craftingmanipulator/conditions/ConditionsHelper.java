package net.fryc.craftingmanipulator.conditions;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class ConditionsHelper {

    public static boolean hasCorrectItemInInventory(PlayerEntity player, @Nullable TagKey<Item> items, @Nullable HashSet<Item> additionalItems){
        for(int i = 0;i < player.getInventory().size(); i++){
            if(isCorrectItem(player.getInventory().getStack(i), items, additionalItems)) return true;
        }
        return false;
    }

    private static boolean isCorrectItem(ItemStack stack, @Nullable TagKey<Item> items, @Nullable HashSet<Item> additionalItems){
        boolean returnValue = false;
        if(items != null){
            returnValue = stack.isIn(items);
        }
        if(!returnValue && additionalItems != null){
            returnValue = additionalItems.contains(stack.getItem());
        }
        return returnValue;
    }

    public static boolean standsNearCorrectBlock(PlayerEntity player, World world , @Nullable TagKey<Block> blocks, @Nullable HashSet<Block> additionalBlocks){
        BlockPos pos = player.getBlockPos();
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-5, -2, -5), pos.add(5, 3, 5))) {
            if(isCorrectBlock(world.getBlockState(blockPos).getBlock(), blocks, additionalBlocks)){
                return true;
            }
        }
        return false;
    }

    private static boolean isCorrectBlock(Block block, @Nullable TagKey<Block> tagBlocks, @Nullable HashSet<Block> additionalBlocks){
        boolean returnValue = false;
        if(tagBlocks != null){
            returnValue = block.getDefaultState().isIn(tagBlocks);
        }
        if(!returnValue && additionalBlocks != null){
            returnValue = additionalBlocks.contains(block);
        }
        return returnValue;
    }

    public static boolean isOnCorrectBiome(PlayerEntity player, World world, @Nullable TagKey<Biome> biomes){
        if(biomes == null) return false;
        return world.getBiomeAccess().getBiome(player.getBlockPos()).isIn(biomes);
    }

    public static boolean playerHasLevel(PlayerEntity player, int playerLevel){
        return player.experienceLevel >= playerLevel;
    }

    /*
    public static boolean detectAndUnlock(UnlockConditions condition, PlayerEntity player, TagKey<?> tag){
        if(condition == UnlockConditions.ITEM_IN_INVENTORY){
            try {
                TagKey<Item> items;
                if (tag.isOf(RegistryKeys.ITEM)) {
                    items = (TagKey<Item>) tag;
                } else {
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule!");
                    return false;
                }
                return hasCorrectItemInInventory(player, items);
            }
            catch (Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking TagKey for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }
        else if(condition == UnlockConditions.BLOCK_NEARBY){
            try{
                TagKey<Block> blocks;
                if(tag.isOf(RegistryKeys.BLOCK)){
                    blocks = (TagKey<Block>)tag;
                }
                else {
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule!");
                    return false;
                }
                return standsNearCorrectBlock(player, player.getWorld(), blocks);
            }
            catch(Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking TagKey for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }
        else if(condition == UnlockConditions.ON_BIOME){
            try {
                TagKey<Biome> biomes;
                if (tag.isOf(RegistryKeys.BIOME)) {
                    biomes = (TagKey<Biome>) tag;
                } else {
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule!");
                    return false;
                }
                return isOnCorrectBiome(player, player.getWorld(), biomes);
            }
            catch(Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking TagKey for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public static boolean detectAndUnlock(UnlockConditions condition, PlayerEntity player, HashSet<?> set){
        if(condition == UnlockConditions.ITEM_IN_INVENTORY){
            try {
                for(var cos : set){
                    if(cos instanceof Item){
                        break;
                    }
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule! Unlock Condition for " + cos.getClass().getName() +
                            " expected, but " + condition.name() + " is present");
                    return false;
                }
                HashSet<Item> items = (HashSet<Item>) set;
                return hasCorrectItemInInventory(player, items);
            }
            catch (Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking HashSet for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }
        else if(condition == UnlockConditions.BLOCK_NEARBY){
            try{
                for(var cos : set){
                    if(cos instanceof Block){
                        break;
                    }
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule! Unlock Condition for " + cos.getClass().getName() +
                            " expected, but " + condition.name() + " is present");
                    return false;
                }
                HashSet<Block> blocks = (HashSet<Block>)set;
                return standsNearCorrectBlock(player, player.getWorld(), blocks);
            }
            catch(Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking HashSet for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }
        else if(condition == UnlockConditions.ON_BIOME){
            try{
                for(var cos : set){
                    if(cos instanceof RegistryKey){
                        break;
                    }
                    CraftingManipulator.LOGGER.error("Wrong UnlockCondition for OCR rule! Unlock Condition for " + cos.getClass().getName() +
                            " expected, but " + condition.name() + " is present");
                    return false;
                }
                HashSet<RegistryKey<Biome>> biomes = (HashSet<RegistryKey<Biome>>)set;
                return isOnCorrectBiome(player, player.getWorld(), biomes);
            }
            catch(Exception e){
                CraftingManipulator.LOGGER.error("Something went wrong while checking HashSet for OCR rule. Error message: " + e.getMessage());
                return false;
            }
        }

        return false;
    }

     */
}
