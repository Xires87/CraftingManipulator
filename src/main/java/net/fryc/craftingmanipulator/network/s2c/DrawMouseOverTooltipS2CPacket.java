package net.fryc.craftingmanipulator.network.s2c;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fryc.craftingmanipulator.network.payloads.DrawMouseOverTooltipPayload;
import net.fryc.craftingmanipulator.util.DrawsSelectedTooltips;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.text.Text;

public class DrawMouseOverTooltipS2CPacket {

    public static void receive(DrawMouseOverTooltipPayload payload, ClientPlayNetworking.Context context){
        ClientPlayerEntity player = context.player();
        if(player != null){
            if(player.currentScreenHandler instanceof AbstractRecipeScreenHandler<?, ?>){
                Text text = payload.text();
                ((DrawsSelectedTooltips) player.currentScreenHandler).addTooltipToDraw(text, payload.x(), payload.y(), payload.width(), payload.height());
            }
        }
    }
}
