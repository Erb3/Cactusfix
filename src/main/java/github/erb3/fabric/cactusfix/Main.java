package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final String MOD_ID = "cactusfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier SYNC_FREER_CACTUS_PLACING = new Identifier(MOD_ID, "sync_freer_cactus_placing");

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
        LOGGER.info("Hello from Cactusfix! Lets get fixing your cactus");
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncFreerCactusRule(handler.player, server.getGameRules().getBoolean(ALLOW_FREER_CACTUS_PLACING)));
    }

    private static void syncFreerCactusRule(ServerPlayerEntity player, boolean value) {
        final PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(value);
        ServerPlayNetworking.send(player, SYNC_FREER_CACTUS_PLACING, buf);
    }
}
