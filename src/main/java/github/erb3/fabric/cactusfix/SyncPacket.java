package github.erb3.fabric.cactusfix;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static github.erb3.fabric.cactusfix.Main.MOD_ID;

public record SyncPacket(boolean allowsFreer) implements CustomPayload {
    public static final CustomPayload.Id<SyncPacket> ID = new CustomPayload.Id<>(new Identifier(MOD_ID, "sync_freer_cactus_placing"));
    public static final PacketCodec<RegistryByteBuf, SyncPacket> PACKET_CODEC = PacketCodecs.BOOL.xmap(SyncPacket::new, SyncPacket::allowsFreer).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
