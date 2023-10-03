package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.GameRules;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(Main.SYNC_FREER_CACTUS_PLACING, (client, handler, buf, responseSender) -> {
            client.execute(() -> {

                final boolean value = buf.readBoolean();

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
