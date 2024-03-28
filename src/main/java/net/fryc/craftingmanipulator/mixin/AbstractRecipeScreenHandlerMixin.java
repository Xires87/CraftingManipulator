package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

@Mixin(AbstractRecipeScreenHandler.class)
abstract class AbstractRecipeScreenHandlerMixin implements DrawsSelectedTextures, DrawsSelectedTooltips {

    private boolean isItemStackModified = false;

    List<Pair<Text, int[]>> tooltipsToDraw = new ArrayList<>();

    public boolean hasEnabledDrawing() {
        return this.isItemStackModified;
    }

    public boolean isItemModified() {
        return this.isItemStackModified;
    }
    public void setItemIsModified(boolean modified) {
        this.isItemStackModified = modified;
    }

    public void informAboutItemModification() {
        this.isItemStackModified = true;
    }

    public void addTooltipToDraw(Text content, int x, int y, int width, int height) {
        this.tooltipsToDraw.add(new Pair<>(content, new int[]{x, y, width, height}));
    }

    public List<Pair<Text, int[]>> getTooltipsToDraw() {
        return this.tooltipsToDraw;
    }
}
