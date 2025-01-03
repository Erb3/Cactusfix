package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

public class CactusfixClient implements ClientModInitializer {
    public static boolean permitsFreerCactusPlacing = false;

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncPacket.SYNC_PACKET_ID, (payload, context) -> {
            MinecraftClient client = context.client();
            client.execute(() -> permitsFreerCactusPlacing = payload.allowsFreer());
        });
    }
}
