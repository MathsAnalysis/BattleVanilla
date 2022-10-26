package it.mathanalisys.vanilla.command;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.data.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StatsCommand extends Command {

    public StatsCommand() {
        super("stats");
        setAliases(List.of("statistiche"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0){
            if (sender instanceof Player player){
                PlayerData playerData = PlayerData.getPlayerConnection(player);
                player.sendMessage("");
                player.sendMessage(CC.translate("&cUccisioni: &f" + playerData.getKills()));
                player.sendMessage(CC.translate("&cMorti: &f" + playerData.getDeaths()));
                player.sendMessage(CC.translate("&cMob Uccisi: &f" + playerData.getMobKills()));
                player.sendMessage(CC.translate("&cBlocchi Rotti: &f" + playerData.getBlockBroken()));
                player.sendMessage("");
                return true;
            }
            return true;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        PlayerData tData = Vanilla.get().getDatabaseManager().findPlayerStatsByUUID(offlinePlayer.getUniqueId().toString());
        if (tData == null){
            sender.sendMessage(CC.translate("&cLe statistiche di &l" + offlinePlayer.getName() + " &cnon sono state trovate!"));
            return true;
        }
        sender.sendMessage(CC.translate("&c&lStatistiche di " + offlinePlayer.getName()));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&cUccisioni: &f" + tData.getKills()));
        sender.sendMessage(CC.translate("&cMorti: &f" + tData.getDeaths()));
        sender.sendMessage(CC.translate("&cMob Uccisi: &f" + tData.getMobKills()));
        sender.sendMessage(CC.translate("&cBlocchi Rotti: &f" + tData.getBlockBroken()));
        sender.sendMessage("");



        return false;
    }
}
