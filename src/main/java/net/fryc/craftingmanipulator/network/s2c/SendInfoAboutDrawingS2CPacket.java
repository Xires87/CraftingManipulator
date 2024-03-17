package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fryc.craftingmanipulator.util.DrawsSelectedTextures;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class SendInfoAboutDrawingS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        ClientPlayerEntity player = client.player;
        if(player != null){
            ((DrawsSelectedTextures) player.currentScreenHandler).informAboutItemModification();
        }
    }
}
