package github.erb3.fabric.cactusfix;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record SyncPacket(boolean allowsFreer) implements CustomPayload {
    public SyncPacket(RegistryByteBuf buf) {
        this(buf.readBoolean());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeBoolean(allowsFreer);
    }

    public static final PacketCodec<RegistryByteBuf, SyncPacket> PACKET_CODEC = PacketCodec.of(SyncPacket::write, SyncPacket::new);
    public static final Id<SyncPacket> SYNC_PACKET_ID = CustomPayload.id("cactusfix/sync_freer_cactus_placing");

    @Override
    public Id<? extends CustomPayload> getId() {
        return SYNC_PACKET_ID;
    }
}
