package net.fryc.craftingmanipulator.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fryc.craftingmanipulator.CraftingManipulator;
import net.fryc.craftingmanipulator.network.payloads.DrawMouseOverTooltipPayload;
import net.fryc.craftingmanipulator.network.payloads.SendInfoAboutDrawingPayload;
import net.fryc.craftingmanipulator.network.payloads.ResetDrawingPayload;
import net.fryc.craftingmanipulator.network.s2c.DrawMouseOverTooltipS2CPacket;
import net.fryc.craftingmanipulator.network.s2c.ResetDrawingS2CPacket;
import net.fryc.craftingmanipulator.network.s2c.SendInfoAboutDrawingS2CPacket;
import net.minecraft.util.Identifier;

public class ModPackets {

    public static final Identifier DRAW_MOUSE_OVER_TOOLTIP = Identifier.of(CraftingManipulator.MOD_ID, "draw_mouse_over_tooltip_id");
    public static final Identifier SEND_INFO_ABOUT_ITEM_MODIFICATION = Identifier.of(CraftingManipulator.MOD_ID, "send_info_about_item_modification_id");
    public static final Identifier RESET_DRAWINGS = Identifier.of(CraftingManipulator.MOD_ID, "reset_drawings_id");

    public static void registerPayloads(){
        PayloadTypeRegistry.playS2C().register(DrawMouseOverTooltipPayload.ID, DrawMouseOverTooltipPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SendInfoAboutDrawingPayload.ID, SendInfoAboutDrawingPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ResetDrawingPayload.ID, ResetDrawingPayload.CODEC);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SendInfoAboutDrawingPayload.ID, SendInfoAboutDrawingS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(ResetDrawingPayload.ID, ResetDrawingS2CPacket::receive);

        ClientPlayNetworking.registerGlobalReceiver(DrawMouseOverTooltipPayload.ID, DrawMouseOverTooltipS2CPacket::receive);
    }
}
