package ru.wowhcb.oreglow;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class OreGlow implements ModInitializer {
	public static final String MOD_ID = "ore-glow";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RegistryEntryCallback.init();
        try {
            ConfigReader.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		for (var entry : ConfigReader.customBlocks.entrySet()) {
			Block block = Registries.BLOCK.get(Identifier.of(entry.getKey()));
			if (block != Blocks.AIR){
				block.getStateManager().getStates().forEach(state -> state.luminance = entry.getValue());
			}
			else {
				OreGlow.LOGGER.debug("Block not found in Block Registry: " +  entry.getKey());
			}
		}
	}
}