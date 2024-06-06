package it.mathanalisys.vanilla.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String translate(Player player, String input){
        return PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', input));
    }

    public static List<String> translateStrings(List<String> untranslated){
        List<String> translated = new ArrayList<>();
        for(String untranslatedString : untranslated){
            translated.add(CC.translate(untranslatedString));
        }
        return translated;
    }

    public static List<String> translateStrings(Player player, List<String> untranslated){
        List<String> translated = new ArrayList<>();
        for(String untranslatedString : untranslated){
            translated.add(CC.translate(player, untranslatedString));
        }
        return translated;
    }
}
