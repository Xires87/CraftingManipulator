package net.fryc.craftingmanipulator.conditions;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ConditionsHelper {

    public static boolean hasCorrectItemInInventory(PlayerEntity player, TagKey<Item> items){
        for(int i = 0;i < player.getInventory().size(); i++){
            if(player.getInventory().getStack(i).isIn(items)) return true;
        }
        return false;
    }

    public static boolean standsNearCorrectBlock(PlayerEntity player, World world , TagKey<Block> blocks){
        BlockPos pos = player.getBlockPos();
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-5, -2, -5), pos.add(5, 3, 5))) {
            if (world.getBlockState(blockPos).isIn(blocks)){
                return true;
            }
        }
        return false;
    }

    public static boolean isOnCorrectBiome(PlayerEntity player, World world, TagKey<Biome> biomes){
        return world.getBiomeAccess().getBiome(player.getBlockPos()).isIn(biomes);
    }
}
