package it.mathanalisys.vanilla.command.staff;

import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LookUpCommand extends Command {

    public LookUpCommand() {
        super("lookup");
        setPermission("vanilla.command.lookup");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (!sender.hasPermission(Objects.requireNonNull(getPermission()))){
            sender.sendMessage(CC.translate("&cNon hai il permesso per poter eseguire questo comando!"));
            return true;
        }

        assert target != null;
        if (!Bukkit.getOnlinePlayers().contains(target)){
            sender.sendMessage(CC.translate("&cIl giocatore non è presente tra quelli online!"));
            return true;
        }

        sender.sendMessage("&8&m--------------");
        sender.sendMessage(CC.translate((target.isOp() ? "&4&lAttento &cquesto giocatore possiede tutti i permessi" : "&cQuesto giocatore non possiede permessi d' amministratore")));
        sender.sendMessage(CC.translate("&cLatenza: &f" + target.spigot().getPing()));
        sender.sendMessage(CC.translate("&cHosting Provider: &f" +  target.getServer().getName()));
        sender.sendMessage("&8&m--------------");


        return false;
    }
}
