package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final String MOD_ID = "cactusfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_CACTUS_DAMAGE_ITEMS =
        GameRuleRegistry.register("shouldCactusDamageItems", GameRules.Category.DROPS,
            GameRuleFactory.createBooleanRule(false));

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_CACTUS_DAMAGE_PLAYERS =
        GameRuleRegistry.register("shouldCactusDamagePlayers", GameRules.Category.PLAYER,
            GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_FREER_CACTUS_PLACING =
        GameRuleRegistry.register("allowFreerCactusPlacing", GameRules.Category.MISC,
            GameRuleFactory.createBooleanRule(false, (server, rule) -> server.getPlayerManager().getPlayerList().forEach(player -> syncFreerCactusRule(player, rule.get()))));

    @Override
    public void onInitialize() {
        LOGGER.info("Cactusfix has awoken.");
        PayloadTypeRegistry.playS2C().register(SyncPacket.SYNC_PACKET_ID, SyncPacket.PACKET_CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncFreerCactusRule(handler.player, server.getGameRules().getBoolean(ALLOW_FREER_CACTUS_PLACING)));
    }

    private static void syncFreerCactusRule(ServerPlayerEntity player, boolean value) {
        ServerPlayNetworking.send(player, new SyncPacket(value));
    }
}
