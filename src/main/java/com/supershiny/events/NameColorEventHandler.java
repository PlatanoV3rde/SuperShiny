package com.supershiny.events;

import com.pixelmonmod.pixelmon.api.events.SpawnEvent;
import com.pixelmonmod.pixelmon.api.events.PixelmonReleaseEvent;
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.supershiny.config.PaletteManager;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(modid = "supershiny")
public class NameColorEventHandler {

    @SubscribeEvent
    public static void onWildSpawn(SpawnEvent event) {
        if (!(event.getEntity() instanceof EntityPixelmon)) return;
        EntityPixelmon pixelmon = (EntityPixelmon) event.getEntity();
        applyColor(pixelmon);
    }

    @SubscribeEvent
    public static void onRelease(PixelmonReleaseEvent event) {
        if (event.getPokemon() != null && event.getPokemon().getEntity() != null) {
            applyColor((EntityPixelmon) event.getPokemon().getEntity());
        }
    }

    @SubscribeEvent
    public static void onBattle(BattleStartEvent event) {
        event.getBattleController().participants.forEach(participant -> {
            participant.allPokemon.forEach(p -> {
                if (p.getEntity() instanceof EntityPixelmon) {
                    applyColor((EntityPixelmon) p.getEntity());
                }
            });
        });
    }

    private static void applyColor(EntityPixelmon pixelmon) {
        String palette = pixelmon.getPokemon().getPalette().name(); // Ej: sshiny
        Color color = PaletteManager.getColor(palette);
        if (color == null) return;

        String code = rgbToMCColor(color.getRed(), color.getGreen(), color.getBlue());
        pixelmon.setCustomName(code + pixelmon.getPokemon().getLocalizedName() + "\u00a7r");
        pixelmon.setCustomNameVisible(true);
    }

    private static String rgbToMCColor(int r, int g, int b) {
        String hex = String.format("%06X", (r << 16) | (g << 8) | b);
        StringBuilder sb = new StringBuilder("\u00a7x");
        for (char c : hex.toCharArray()) {
            sb.append("\u00a7").append(c);
        }
        return sb.toString();
    }
}
