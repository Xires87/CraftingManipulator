package net.fryc.craftingmanipulator.network.payloads;

import net.fryc.craftingmanipulator.network.ModPackets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;

public record DrawMouseOverTooltipPayload(Text text, int x, int y, int width, int height) implements CustomPayload {

    public static final Id<DrawMouseOverTooltipPayload> ID = new Id<>(ModPackets.DRAW_MOUSE_OVER_TOOLTIP);
    public static final PacketCodec<RegistryByteBuf, DrawMouseOverTooltipPayload> CODEC = PacketCodec.tuple(
            TextCodecs.PACKET_CODEC, DrawMouseOverTooltipPayload::text,
            PacketCodecs.INTEGER, DrawMouseOverTooltipPayload::x,
            PacketCodecs.INTEGER, DrawMouseOverTooltipPayload::y,
            PacketCodecs.INTEGER, DrawMouseOverTooltipPayload::width,
            PacketCodecs.INTEGER, DrawMouseOverTooltipPayload::height,
            DrawMouseOverTooltipPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}