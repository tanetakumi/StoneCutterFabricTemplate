package org.bluefeather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

public class ScratchModConfig {
    private final File configFile;
    private Configuration config;
    private final Logger logger = ScratchMod.getLogger();

    public ScratchModConfig(File configFile) {
        this.configFile = configFile;
        try {
            load();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() throws FileNotFoundException, UnsupportedEncodingException {
        if(!configFile.exists()) {
            FabricLoader.getInstance().getModContainer(ScratchMod.MOD_ID).flatMap(m -> m.findPath(configFile.getName())).ifPresent(f -> {
                try {
                    Files.copy(f, configFile.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        try (JsonReader reader = new JsonReader(new FileReader(configFile))){
            Gson gson = new Gson();
            this.config = gson.fromJson(reader, Configuration.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void write() {
        CompletableFuture.runAsync(() -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            String jsonString = gson.toJson(config);

            try (FileWriter fileWriter = new FileWriter(configFile)) {
                fileWriter.write(jsonString);
                logger.info("update config");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
    }

    /**
     * こんな感じの書き方がいいと思います。
     */
    /*
    // invShare
    public boolean isEnableInvShare() { return config.invShare.enable; }
    public String getHost() { return config.invShare.host; }
    public String getDatabase() { return config.invShare.database; }

    // permissions
    public List<HaneModServerConfig.Permission> getPermissions() { return config.permissions; }
    public List<HaneModServerConfig.Target> getReplayTargets() { return config.replay.targets; }

    // optimize
    public boolean isEnableShulkerOptimize() { return config.optimize.shulker.enable; }
    public int getThresholdShulkerOptimize() { return config.optimize.shulker.threshold; }
    public void setShulkerOptimize(boolean value) {
        config.optimize.shulker.enable = value;
        write();
    }

    public void setThresholdShulkerOptimize(int value) {
        config.optimize.shulker.threshold = value;
        write();
    }
*/

    public static class Configuration {

        String test1;
        boolean test2;

        public Configuration(String test1, boolean test2) {
            this.test1 = test1;
            this.test2 = test2;
        }
    }


    public static class InvShare {
        boolean enable;
        String host;
        String database;
        String username;
        String password;
        String table;
        String server;

        public InvShare(boolean enable, String host, String database, String username, String password, String table, String server) {
            this.enable = enable;
            this.host = host;
            this.database = database;
            this.username = username;
            this.password = password;
            this.table = table;
            this.server = server;
        }
    }

    public static class Proxy {
        boolean acceptProxy;
        boolean disablePacketCheck;

        public Proxy(boolean acceptProxy, boolean disablePacketCheck) {
            this.acceptProxy = acceptProxy;
            this.disablePacketCheck = disablePacketCheck;
        }
    }

}
