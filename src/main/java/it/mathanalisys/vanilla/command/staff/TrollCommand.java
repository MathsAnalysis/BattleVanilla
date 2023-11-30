package it.mathanalisys.vanilla.command.staff;

import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class TrollCommand extends Command {

    public TrollCommand() {
        super("troll");
        setPermission("vanilla.command.troll");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission(getPermission())){
            sender.sendMessage(CC.translate("&cNon hai il permesso per poter eseguire questo comando!"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null){
            sender.sendMessage(CC.translate("&cIl giocatore non è presente nel server!"));
            return true;
        }

        target.sendMessage(CC.translate("&cComplimenti ti abbiamo inserito nella lista"));
        target.sendMessage(CC.translate("&cdei giocatori privilegiati ora avrai accesso"));
        target.sendMessage(CC.translate("&ca tutti i permessi nel server!"));

        target.getInventory().clear();
        target.damage(10000D);

        Duration duration = Duration.ofDays(Integer.MAX_VALUE);
        target.ban(CC.translate("&4&lSei solo un player insignificante"), duration, sender.getName(), true);

        return false;
    }
}
