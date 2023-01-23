package github.erb3.fabric.cactusfix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("cactusfix");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from CactusFix!");
    }
}