package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fryc.craftingmanipulator.CraftingManipulator;
import net.fryc.craftingmanipulator.gui.Drawing;
import net.fryc.craftingmanipulator.registry.CMRegistries;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.AbstractRecipeScreenHandler;

public class SendInfoAboutDrawingS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        ClientPlayerEntity player = client.player;
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?>){
                ((DrawsSelectedTextures) player.currentScreenHandler).informAboutItemModification();

                String id;
                while(buf.isReadable()){
                    id = buf.readString();
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
