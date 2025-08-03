package main.java.com.platanov3rde.supershiny;

import com.pixelmonmod.api.events.SpawnEvent;
import com.pixelmonmod.api.pokemon.Pokemon;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "sshinyname")
public class EventSubscriber {

    @SubscribeEvent
    public static void onPokemonSpawn(SpawnEvent.Post event) {
        Pokemon pokemon = event.getPokemon();
        // Obtener nombre de la palette en minúsculas
        String palette = pokemon.getForm().getPalette().getName().toLowerCase();

        // Si la palette está incluida en config.yml, coloreamos el nombre
        if (ConfigManager.palettes.contains(palette)) {
            String baseName = pokemon.getPokemonName();
            // Creamos un componente con color dorado
            StringTextComponent goldName = new StringTextComponent(TextFormatting.GOLD + baseName);
            pokemon.getEntity().setCustomName(goldName);
            pokemon.getEntity().setCustomNameVisible(true);
        }
    }
}
