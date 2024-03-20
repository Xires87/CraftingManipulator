package net.fryc.craftingmanipulator.util;

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
}
