package dev.killergamerpls.vanilla.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> translateStrings(List<String> untranslated){
        List<String> translated = new ArrayList<>();
        for(String untranslatedString : untranslated){
            translated.add(ChatColor.translateAlternateColorCodes('&', untranslatedString));
        }
        return translated;
    }
}
