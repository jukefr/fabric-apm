package fr.juke.fabric_apm;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FabricAPM implements ModInitializer {
	public static Logger logger = LogManager.getLogger(FabricAPM.class);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}
}
