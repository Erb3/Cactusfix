package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cactusfix");

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_CACTUS_DAMAGE_ITEMS =
        GameRuleRegistry.register("shouldCactusDamageItems", GameRules.Category.DROPS,
            GameRuleFactory.createBooleanRule(false));

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_CACTUS_DAMAGE_PLAYERS =
        GameRuleRegistry.register("shouldCactusDamagePlayers", GameRules.Category.PLAYER,
            GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.BooleanRule> BETTER_CACTUS_PLACING =
        GameRuleRegistry.register("doBetterCactusPlacing", GameRules.Category.MISC,
            GameRuleFactory.createBooleanRule(false));

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from Cactusfix! Lets get fixing your cactus");
    }
}
