package it.mathanalisys.vanilla.command;

import com.mongodb.client.model.Filters;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import org.bson.Document;
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
                PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
                player.sendMessage(CC.translate("&5&lStatistiche"));
                player.sendMessage("");
                player.sendMessage(CC.translate("&dUccisioni: &7" + playerData.getKills()));
                player.sendMessage(CC.translate("&dMorti: &7" + playerData.getDeaths()));
                player.sendMessage(CC.translate("&dMob Uccisi: &7" + playerData.getMobKills()));
                player.sendMessage(CC.translate("&dBlocchi Rotti: &7" + playerData.getBlockBroken()));
                player.sendMessage("");
                return true;
            }
            return true;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        Document document = Vanilla.get().getDatabaseManager().getPlayers().find(Filters.eq("name", args[0])).first();
        if (document == null) {sender.sendMessage(CC.translate("&cStatistiche non trovate di &l" + offlinePlayer.getName() + "&c!"));return true;}
        PlayerData tData = PlayerData.getByUuid(offlinePlayer.getUniqueId());
        sender.sendMessage(CC.translate("&5&lStatistiche di " + offlinePlayer.getName()));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&dUccisioni: &7" + tData.getKills()));
        sender.sendMessage(CC.translate("&dMorti: &7" + tData.getDeaths()));
        sender.sendMessage(CC.translate("&dMob Uccisi: &7" + tData.getMobKills()));
        sender.sendMessage(CC.translate("&dBlocchi Rotti: &7" + tData.getBlockBroken()));
        sender.sendMessage("");

        return false;
    }
}
