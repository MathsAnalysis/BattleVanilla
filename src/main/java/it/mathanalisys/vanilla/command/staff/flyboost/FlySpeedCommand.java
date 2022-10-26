package it.mathanalisys.vanilla.command.staff.flyboost;

import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FlySpeedCommand extends Command {

    public FlySpeedCommand() {
        super("flyboost");
        setAliases(List.of("fly"));
        setPermission("vanilla.command.flyboost");

    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(CC.translate("&cPermessi Insufficienti."));
            return true;
        }
        FlyBoostMenu.getInventory((Player) sender).open((Player) sender);
        return false;
    }
}
