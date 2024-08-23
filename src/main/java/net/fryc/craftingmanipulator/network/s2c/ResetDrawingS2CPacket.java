package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.network.payloads.ResetDrawingPayload;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.screen.AbstractRecipeScreenHandler;

public class ResetDrawingS2CPacket {

    public static void receive(ResetDrawingPayload payload, ClientPlayNetworking.Context context){
        ClientPlayerEntity player = context.player();
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?, ?>){
                ((DrawsSelectedTextures) player.currentScreenHandler).setItemIsModified(false);
                if (((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().size() > 0) {
                    //jak teraz patrze to nie mam pojecia po co ten sublist ale wole nie tykac bo pewnie mialem powod zeby to dac
                    ((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().subList(0, ((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().size()).clear();
                }
            }

        }
        for(Drawing drawing : CMRegistries.DRAWINGS.values()){
            drawing.enabled = false;
        }
    }
}
