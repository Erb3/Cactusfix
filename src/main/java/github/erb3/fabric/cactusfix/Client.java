package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameRules;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(SyncPacket.ID, SyncPacket.PACKET_CODEC);

        //noinspection CodeBlock2Expr
        ClientPlayNetworking.registerGlobalReceiver(SyncPacket.ID, (payload, context) -> {
            MinecraftClient.getInstance().execute(() -> {
                final boolean value = payload.allowsFreer();
                MinecraftClient client = context.client();
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
