package it.mathanalisys.vanilla.command.staff;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class VanillaReloadConfigCommand extends Command {

    public VanillaReloadConfigCommand() {
        super("vanillareload");
        setPermission("vanilla.command.reload");
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission(getPermission())) {
            Vanilla.get().getScoreboardConfig().reload();
            Vanilla.get().getScoreboardConfig().save();
            commandSender.sendMessage(CC.translate("&aConfig reloaded!"));
            return true;
        }
        return false;
    }
}
