package com.supershiny.config;

import org.yaml.snakeyaml.Yaml;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PaletteManager {

    private static final Map<String, Color> PALETTE_MAP = new HashMap<>();

    public static void load() {
        try {
            Path path = net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get()
                .resolve("supershiny/config.yml");
            if (!Files.exists(path)) return;

            InputStream input = Files.newInputStream(path);
            Yaml yaml = new Yaml();

            Map<String, Object> root = yaml.load(input);
            Map<String, Map<String, Integer>> palettes = (Map<String, Map<String, Integer>>) root.get("Paletas");

            for (Map.Entry<String, Map<String, Integer>> entry : palettes.entrySet()) {
                String name = entry.getKey();
                Map<String, Integer> rgb = entry.getValue();
                Color color = new Color(
                    rgb.getOrDefault("R", 255),
                    rgb.getOrDefault("G", 255),
                    rgb.getOrDefault("B", 255)
                );
                PALETTE_MAP.put(name.toLowerCase(), color);
            }

        } catch (Exception e) {
            System.err.println("[SuperShiny] Error cargando config.yml: " + e.getMessage());
        }
    }

    public static Color getColor(String paletteName) {
        return PALETTE_MAP.get(paletteName.toLowerCase());
    }
}
