package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.text.Text;

public class DrawMouseOverTooltipS2CPacket {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        ClientPlayerEntity player = client.player;
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?>){
                Text text = buf.readText();
                int x = buf.readInt();
                int y = buf.readInt();
                int width = buf.readInt();
                int height = buf.readInt();
                ((DrawsSelectedTooltips) player.currentScreenHandler).addTooltipToDraw(text, x, y, width, height);
            }
        }
    }
}
