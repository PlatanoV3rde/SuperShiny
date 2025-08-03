package com.supershiny;

import com.supershiny.config.PaletteManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("supershiny")
public class SuperShiny {

    public SuperShiny() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PaletteManager.load(); // Cargar configuraciones
    }
}
