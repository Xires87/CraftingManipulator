package net.fryc.craftingmanipulator.mixin;

import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreen.class)
abstract class CraftingScreenMixin extends HandledScreen<CraftingScreenHandler> implements RecipeBookProvider {

    private static final Identifier TEXTURE = new Identifier("craftingmanipulator", "textures/gui/crafting_table_modified.png");

    public CraftingScreenMixin(CraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(at = @At("TAIL"), method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V")
    protected void drawThingsFromRules(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo info) {
        CraftingScreen dys = ((CraftingScreen)(Object)this);
        if(((DrawsSelectedTextures) dys.getScreenHandler()).hasEnabledDrawing()){
            context.drawTexture(TEXTURE, this.x + 87, this.y + 32, this.backgroundWidth, 0, 28, 21); // todo chyba zrobie tak ze zrobie 2 klasy jedna od rysowania tekstur druga od tooltipow i jakos to bedzie
            if(this.isPointWithinBounds(87, 32, 28, 21, (double)mouseX, (double)mouseY)){
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(Text.of("test"), 115), mouseX, mouseY);
            }
        }
    }
}
