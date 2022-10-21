package dev.killergamerpls.vanilla.command.staff;

import dev.killergamerpls.vanilla.Vanilla;
import dev.killergamerpls.vanilla.backend.data.PlayerData;
import dev.killergamerpls.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResetStatsCommand extends Command {

    public ResetStatsCommand() {
        super("resetstats");
        setAliases(List.of("rs"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        PlayerData playerData = Vanilla.get().getDatabaseManager().findPlayerStatsByUUID(target.getUniqueId().toString());
        if(playerData == null) {
            sender.sendMessage(CC.translate("&cNon è stato trovato nessun player nel datbase!"));
            return true;
        }
        Vanilla.get().getDatabaseManager().deletePlayerStats(playerData);
        sender.sendMessage(CC.translate("&aSono appena state cancellate le statistiche di &l" + target.getName()));
        return false;
    }
}
