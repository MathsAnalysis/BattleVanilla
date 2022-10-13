package dev.killergamerpls.vanilla.command;

import dev.killergamerpls.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport");
        setAliases(List.of("tpto"));
        setPermission("vanilla.command.teleport");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())){
            sender.sendMessage(CC.translate("&cPermessi Insufficienti"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        return false;
    }
}
