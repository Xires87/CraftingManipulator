package net.fryc.craftingmanipulator.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Pair;

@Mixin(InventoryScreen.class)
abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(at = @At("TAIL"), method = "drawBackground(Lnet/minecraft/client/util/math/MatrixStack;FII)V")
    protected void drawThingsFromRulesOnInventoryScreen(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo info) {
        InventoryScreen dys = ((InventoryScreen)(Object)this);
        if(((DrawsSelectedTextures) dys.getScreenHandler()).hasEnabledDrawing()){
            for(Drawing drawing : CMRegistries.DRAWINGS.values()){
                if(drawing.isEnabled(dys.getScreenHandler().getClass())){
                    if(drawing.getTexture() != null){
                        RenderSystem.setShaderTexture(0, drawing.getTexture());
                        this.drawTexture(
                                matrices, this.x + drawing.getX(), this.y + drawing.getY(),
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
                renderOrderedTooltip(matrices, this.textRenderer.wrapLines(text, 115), mouseX, mouseY);
            }
        }
    }
}
