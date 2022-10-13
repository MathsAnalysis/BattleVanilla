package dev.killergamerpls.vanilla.command.staff.velocity;

import dev.killergamerpls.vanilla.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VelocityCommand extends Command {

    public VelocityCommand() {
        super("velocity");
        setAliases(List.of("v"));
        setPermission("vanilla.command.flyboost");

    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(CC.translate("&cPermessi Insufficienti."));
            return true;
        }
        VelocityMenu.getInventory((Player) sender).open((Player) sender);
        return false;
    }
}
