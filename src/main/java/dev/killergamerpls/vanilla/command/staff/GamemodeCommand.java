package dev.killergamerpls.vanilla.command.staff;

import dev.killergamerpls.vanilla.utils.CC;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommand extends Command {

    public GamemodeCommand() {
        super("gamemode");
        setAliases(List.of("gm"));
        setPermission("vanilla.command.gamemode");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(getPermission())){
            sender.sendMessage(CC.translate("&cPermessi Insufficienti"));
            return true;
        }

        if(args.length == 0) {
            sender.sendMessage(CC.translate("Usage: /gamemode <c|s|a|sp> <player>"));
            return true;
        }

        if (args.length == 1){
            if (!(sender instanceof Player player)){
                sender.sendMessage(CC.translate("&cGiocatore non trovato!"));
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "1", "c", "0", "s", "2", "a", "3", "sp" -> {
                    GameMode gameMode = List.of("1", "c").contains(args[0].toLowerCase()) ? GameMode.CREATIVE
                            : List.of("0", "s").contains(args[0].toLowerCase()) ? GameMode.SURVIVAL
                            : List.of("2", "a").contains(args[0].toLowerCase()) ? GameMode.ADVENTURE
                            : List.of("3", "sp").contains(args[0].toLowerCase()) ? GameMode.SPECTATOR : GameMode.SURVIVAL;
                    player.setGameMode(gameMode);
                    player.sendMessage(CC.translate("&aLa tua modalità di gioco è stata cambiata in " + WordUtils.capitalizeFully(gameMode.name())));
                }
                default -> player.sendMessage(ChatColor.RED + "Usage: /gamemode <c|s|a|sp>");
            }
        }
        return true;
    }
}