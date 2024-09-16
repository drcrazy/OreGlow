// Heavily based on https://github.com/Apothicon02/Glowing-Ores/blob/master/src/main/java/com/Apothic0n/GlowingOres/GloreJsonReader.java

package ru.wowhcb.oreglow;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ConfigReader {
    public static Map<String, Integer> customBlocks;

    public static void init() throws IOException {
        Path configPath = Path.of(FabricLoader.getInstance().getConfigDir() + "/oreglow.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!Files.exists(configPath)) {
            Files.write(configPath, ("{\n" +
                    "  \"minecraft:coal_ore\":4,\n" +
                    "  \"minecraft:deepslate_coal_ore\":4,\n" +
                    "  \"minecraft:iron_ore\":4,\n" +
                    "  \"minecraft:deepslate_iron_ore\":4,\n" +
                    "  \"minecraft:copper_ore\":7,\n" +
                    "  \"minecraft:deepslate_copper_ore\":7,\n" +
                    "  \"minecraft:lapis_ore\":7,\n" +
                    "  \"minecraft:deepslate_lapis_ore\":7,\n" +
                    "  \"minecraft:gold_ore\":10,\n" +
                    "  \"minecraft:deepslate_gold_ore\":10,\n" +
                    "  \"minecraft:emerald_ore\":12,\n" +
                    "  \"minecraft:deepslate_emerald_ore\":12,\n" +
                    "  \"minecraft:diamond_ore\":15,\n" +
                    "  \"minecraft:deepslate_diamond_ore\":15\n" +
                    "}").getBytes());
        }
        JsonReader reader = new JsonReader(new FileReader(configPath.toString()));
        JsonObject data = gson.fromJson(reader, JsonObject.class);

        Map<String, Integer> tempCustomBlocks = new java.util.HashMap<>(Map.of());
        data.asMap().forEach((block, brightness) -> tempCustomBlocks.put(block, brightness.getAsInt()));

        customBlocks = tempCustomBlocks;
    }
}
