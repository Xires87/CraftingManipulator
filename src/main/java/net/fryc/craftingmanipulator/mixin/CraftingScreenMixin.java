package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;

@Mixin(CraftingScreen.class)
abstract class CraftingScreenMixin extends HandledScreen<CraftingScreenHandler> implements RecipeBookProvider {


    public CraftingScreenMixin(CraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(at = @At("TAIL"), method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V")
    protected void drawThingsFromRules(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {
        CraftingScreen dys = ((CraftingScreen)(Object)this);
        if(((DrawsSelectedTextures) dys.getScreenHandler()).hasEnabledDrawing()){
            for(Drawing drawing : CMRegistries.DRAWINGS.values()){
                if(drawing.isEnabled(dys.getScreenHandler().getClass())){
                    if(drawing.getTexture() != null){
                        context.drawTexture(
                                drawing.getTexture(), this.x + drawing.getX(), this.y + drawing.getY(),
                                this.backgroundWidth + drawing.getxInFile(), drawing.getyInFile(), drawing.getWidth(), drawing.getHeight()
                        );
                    }
                }
            }


        }
        for(Pair<Text, int[]> pair : ((DrawsSelectedTooltips) dys.getScreenHandler()).getTooltipsToDraw()){
            Text text = pair.getA();
            int[] ints = pair.getB();
            if(this.isPointWithinBounds(ints[0], ints[1], ints[2], ints[3], (double)mouseX, (double)mouseY)){
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 115), mouseX, mouseY);
            }
        }
    }
}
