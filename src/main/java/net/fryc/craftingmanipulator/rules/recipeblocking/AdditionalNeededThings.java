package net.fryc.craftingmanipulator.rules.recipeblocking;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public interface AdditionalNeededThings {

    @NotNull HashSet<?> getAdditionalNeededThings();
    boolean isAdditionalNeededThingsNull();
}
