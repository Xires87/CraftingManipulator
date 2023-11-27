package net.fryc.craftingmanipulator.rules;

import net.minecraft.entity.player.PlayerEntity;

public interface ConditionChecker {

    boolean conditionsAreMet(PlayerEntity player);
}
