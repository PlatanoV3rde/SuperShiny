package com.platanov3rde.supershiny;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("sshinyname")
public class SShinyNameMod {

    public SShinyNameMod() {
        FMLJavaModLoadingContext
            .get()
            .getModEventBus()
            .addListener(this::onCommonSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        ConfigManager.loadConfig();
    }
}
