package net.fryc.craftingmanipulator.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fryc.craftingmanipulator.CraftingManipulator;
import net.fryc.craftingmanipulator.network.s2c.ResetDrawingS2CPacket;
import net.fryc.craftingmanipulator.network.s2c.SendInfoAboutDrawingS2CPacket;
import net.minecraft.util.Identifier;

public class ModPackets {

    public static final Identifier SEND_INFO_ABOUT_ITEM_MODIFICATION = new Identifier(CraftingManipulator.MOD_ID, "send_info_about_item_modification_id");
    public static final Identifier RESET_DRAWING = new Identifier(CraftingManipulator.MOD_ID, "reset_drawing_id");

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SEND_INFO_ABOUT_ITEM_MODIFICATION, SendInfoAboutDrawingS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(RESET_DRAWING, ResetDrawingS2CPacket::receive);
    }
}
