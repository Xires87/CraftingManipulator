package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fryc.craftingmanipulator.CraftingManipulator;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.network.payloads.SendInfoAboutDrawingPayload;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.StringHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.screen.AbstractRecipeScreenHandler;

public class SendInfoAboutDrawingS2CPacket {

    public static void receive(SendInfoAboutDrawingPayload payload, ClientPlayNetworking.Context context){
        ClientPlayerEntity player = context.player();
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?, ?>){
                ((DrawsSelectedTextures) player.currentScreenHandler).informAboutItemModification();

                String[] array = StringHelper.convertToStringArray(':', payload.drawings());
                for(String id : array){
                    Drawing drawing = CMRegistries.DRAWINGS.get(id);
                    if(drawing != null){
                        drawing.enabled = true;
                    }
                    else{
                        CraftingManipulator.LOGGER.warn("Unable to get drawing with following id: " + id);
                    }
                }
            }
        }
    }
}
