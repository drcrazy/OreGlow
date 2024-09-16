package ru.wowhcb.oreglow;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class RegistryEntryCallback implements RegistryEntryAddedCallback<Block> {

    public static void init() {
        RegistryEntryAddedCallback.event(Registries.BLOCK).register(new RegistryEntryCallback());
    }

    @Override
    public void onEntryAdded(int rawId, Identifier id, Block block) {

        String id_string = id.toString();

        if (!ConfigReader.customBlocks.containsKey(id_string)) {
            return;
        }
        OreGlow.LOGGER.debug("Got Registry Event for block: " +  id_string);
        int luminance = ConfigReader.customBlocks.get(id_string);
        block.getStateManager().getStates().forEach(state -> state.luminance = luminance);
    }
}
