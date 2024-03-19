package net.fryc.craftingmanipulator.util;

import net.minecraft.text.Text;
import oshi.util.tuples.Pair;

import java.util.List;

public interface DrawsSelectedTooltips {

    void addTooltipToDraw(Text content, int x, int y, int width, int height);
    List<Pair<Text, int[]>> getTooltipsToDraw();

}
