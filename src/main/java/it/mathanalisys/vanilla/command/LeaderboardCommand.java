package it.mathanalisys.vanilla.command;

import it.mathanalisys.vanilla.leaderboard.LeaderboardMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LeaderboardCommand extends Command {

    public LeaderboardCommand() {
        super("leaderboard");
        setAliases(List.of("classifica"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        LeaderboardMenu.getInventory((Player) sender).open((Player) sender);
        return false;
    }
}
