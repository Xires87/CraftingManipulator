package net.fryc.craftingmanipulator.rules.recipeblocking;

import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public interface HasUnlockCondition<T> {

    @Nullable TagKey<T> getUnlockTag();
    HashSet<T> getOrCreateUnlockThings();
    void setUnlockThings(@Nullable HashSet<T> unlockThings);


}
