package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameRules;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncPacket.SYNC_PACKET_ID, (payload, context) -> {
            MinecraftClient client = context.client();
            client.execute(() -> {
                final boolean value = payload.allowsFreer();
                if (client.world != null) {
                    final GameRules rules = client.world.getGameRules();

                    if (rules != null) {
                        final GameRules.BooleanRule rule = rules.get(Main.ALLOW_FREER_CACTUS_PLACING);

                        if (rule != null) {
                            rule.set(value, null);
                        }
                    }
                }
            });
        });
    }
}
