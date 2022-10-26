package it.mathanalisys.vanilla.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Stream;

public class PlayerUtils {

    public static boolean specialUser(Player player){
        if(player.getName().equalsIgnoreCase("KillerGamerPls")){
            return true;
        }
        return false;
    }


}
