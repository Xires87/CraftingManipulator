package net.fryc.craftingmanipulator.network.payloads;

import net.fryc.craftingmanipulator.network.ModPackets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ResetDrawingPayload(boolean bl) implements CustomPayload {

    public static final Id<ResetDrawingPayload> ID = new Id<>(ModPackets.RESET_DRAWINGS);
    public static final PacketCodec<RegistryByteBuf, ResetDrawingPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, ResetDrawingPayload::bl,
            ResetDrawingPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}