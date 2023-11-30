package it.mathanalisys.vanilla.command.staff;

import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport");
        setAliases(List.of("tpto"));
        setPermission("vanilla.command.teleport");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(Objects.requireNonNull(getPermission()))){
            sender.sendMessage(CC.translate("&cPermessi Insufficienti"));
            return true;
        }

        Player player = (Player)sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null){
            sender.sendMessage(CC.translate("Il giocatore richiesto non è online!"));
            return true;
        }

        player.teleport(target.getLocation());


        return true;
    }
}
