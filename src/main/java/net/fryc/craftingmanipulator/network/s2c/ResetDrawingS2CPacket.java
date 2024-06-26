package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.AbstractRecipeScreenHandler;

public class ResetDrawingS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        ClientPlayerEntity player = client.player;
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?>){
                ((DrawsSelectedTextures) player.currentScreenHandler).setItemIsModified(false);
                if (((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().size() > 0) {
                    ((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().subList(0, ((DrawsSelectedTooltips) player.currentScreenHandler).getTooltipsToDraw().size()).clear();
                }
            }

        }
        for(Drawing drawing : CMRegistries.DRAWINGS.values()){
            drawing.enabled = false;
        }
    }
}
