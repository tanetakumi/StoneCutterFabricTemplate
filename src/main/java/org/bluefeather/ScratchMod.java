package org.bluefeather;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScratchMod implements ModInitializer {
    public static final String MOD_ID = "scratch";
    private static final String CLIENT_CONFIG = "scratchmod-client.json";
    private static final String SERVER_CONFIG = "scratchmod-server.json";
    private static Logger logger;
    @Override
    public void onInitialize() {
        logger = LoggerFactory.getLogger(MOD_ID);
        logger.info("-- ModInitializer --");

        //? if >=1.20 {
        logger.info(">=1.20");
        //?} else {
        /* logger.info("!>=1.20"); */
        //?}
    }
    public static Logger getLogger() {
        return logger;
    }

    public static String getServerConfig() {
        return SERVER_CONFIG;
    }
}
