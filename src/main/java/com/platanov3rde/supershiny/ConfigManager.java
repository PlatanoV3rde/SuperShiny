package com.platanov3rde.supershiny;

import net.minecraftforge.fml.loading.FMLPaths;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigManager {
    private static final Path CONFIG_DIR  = FMLPaths.CONFIGDIR.get().resolve("SuperShiny");
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("config.yml");

    /** Conjunto de paletas (en minúsculas) que deberán recibir el nombre dorado */
    public static Set<String> palettes = Collections.emptySet();

    /** Carga o crea la configuración en config/SuperShiny/config.yml */
    public static void loadConfig() {
        try {
            // 1) Asegura que exista la carpeta
            if (Files.notExists(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
            }
            // 2) Si no existe el config.yml, lo crea con un valor por defecto
            if (Files.notExists(CONFIG_FILE)) {
                String defaultCfg = "palettes:\n  - sshiny\n";
                Files.write(CONFIG_FILE, defaultCfg.getBytes(), StandardOpenOption.CREATE);
            }

            // 3) Lee y parsea el YAML
            try (InputStream in = Files.newInputStream(CONFIG_FILE)) {
                Yaml yaml = new Yaml();
                Object data = yaml.load(in);
                if (data instanceof java.util.Map) {
                    Object list = ((java.util.Map<?,?>) data).get("palettes");
                    if (list instanceof List) {
                        palettes = ((List<?>) list).stream()
                            .filter(String.class::isInstance)
                            .map(Object::toString)
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());
                        return;
                    }
                }
            }

            // Si hubo algún problema al parsear, dejamos el set vacío
            palettes = Collections.emptySet();
        } catch (IOException e) {
            e.printStackTrace();
            palettes = Collections.emptySet();
        }
    }
}
