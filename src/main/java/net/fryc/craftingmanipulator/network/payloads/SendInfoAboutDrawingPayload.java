package net.fryc.craftingmanipulator.network.payloads;

import net.fryc.craftingmanipulator.network.ModPackets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SendInfoAboutDrawingPayload(String drawings) implements CustomPayload {

    public static final Id<SendInfoAboutDrawingPayload> ID = new Id<>(ModPackets.SEND_INFO_ABOUT_ITEM_MODIFICATION);
    public static final PacketCodec<RegistryByteBuf, SendInfoAboutDrawingPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, SendInfoAboutDrawingPayload::drawings,
            SendInfoAboutDrawingPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}