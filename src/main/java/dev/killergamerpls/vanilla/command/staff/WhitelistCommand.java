package dev.killergamerpls.vanilla.command.staff;

import dev.killergamerpls.vanilla.utils.CC;
import net.minecraft.server.players.WhiteList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WhitelistCommand extends Command {

    public WhitelistCommand() {
        super("whitelist");
        setAliases(List.of("wl"));
        setPermission("vanilla.command.whitelist");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())){
            sender.sendMessage(CC.translate("&cNon hai il permesso per poter eseguire questo comando!"));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "on"-> {
                if (Bukkit.getServer().hasWhitelist()){
                    sender.sendMessage(CC.translate("&cIl server ha già la whitelist attiva!"));
                    return true;
                }
                Bukkit.setWhitelist(true);
                Bukkit.reloadWhitelist();
                sender.sendMessage(CC.translate("&aLa whitelist è ora attiva!"));
            }
            case "off"-> {
                if (!Bukkit.getServer().hasWhitelist()){
                    sender.sendMessage(CC.translate("&cIl server non ha la whitelist!"));
                    return true;
                }
                Bukkit.setWhitelist(false);
                Bukkit.reloadWhitelist();
                sender.sendMessage(CC.translate("&aLa whitelist è ora disattivata!"));
            }
            case "add"->{
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                if (args.length != 2){
                    sender.sendMessage(CC.translate("&cUsage: /whitelist add <playerName>"));
                    return true;
                }

                if (Bukkit.getServer().getWhitelistedPlayers().contains(player)){
                    sender.sendMessage(CC.translate("&cPlayer già inserito nella whitelist!"));
                    return true;
                }
                player.setWhitelisted(true);
                Bukkit.reloadWhitelist();
                sender.sendMessage(CC.translate("&aHai aggiunto il giocatore &l" + player.getName() + " &acon successo!"));
            }
            case "remove"->{
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                if (args.length != 2){
                    sender.sendMessage(CC.translate("&cUsage: /whitelist remove <playerName>"));
                    return true;
                }

                if (!Bukkit.getServer().getWhitelistedPlayers().contains(player)){
                    sender.sendMessage(CC.translate("&cPlayer già rimosso dalla whitelist!"));
                    return true;
                }

                player.setWhitelisted(false);
                Bukkit.reloadWhitelist();
                sender.sendMessage(CC.translate("&aHai rimosso il giocatore &l" + player.getName() + " &acon successo!"));
            }
            case "list"->{
                sender.sendMessage(CC.translate("&8&m-----------------------"));
                sender.sendMessage(CC.translate("&4&lGiocatori nella whitelist: "));
                sender.sendMessage("");
                Bukkit.getServer().getWhitelistedPlayers().stream()
                        .map(name-> CC.translate("&7- " + ChatColor.RED + name.getName().replace("<:>", "\n ")))
                        .forEach(sender::sendMessage);
                sender.sendMessage("");
                sender.sendMessage(CC.translate("&8&m-----------------------"));
            }
            case "clear"->{
                WhiteList whitelist = ((CraftServer)Bukkit.getServer()).getHandle().i();
                whitelist.getValues().clear();
                sender.sendMessage(CC.translate("&cTutti i giocatori sono stati rimossi dalla whitelist!"));
            }
        }


        return false;
    }
}
