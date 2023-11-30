package it.mathanalisys.vanilla.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

@UtilityClass
public class PlayerUtils {

    public boolean specialUser(Player player){
        return player.getName().equalsIgnoreCase("KillerGamerPls");
    }

    /**
     * Register command(s) into the server command map.
     * @param commands The command(s) to register
     */
    public static void registerCommands(Command... commands) {
        Field commandMapField = null;
        CommandMap commandMap = null;

        // Get the commandMap
        try {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        // Register all the commands into the map
        for (final Command command : commands) {
            commandMap.register(command.getLabel(), command);
        }
    }
}
