package net.fryc.craftingmanipulator;

import net.fabricmc.api.ClientModInitializer;
import net.fryc.craftingmanipulator.network.ModPackets;

public class CraftingManipulatorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModPackets.registerS2CPackets();
    }
}
