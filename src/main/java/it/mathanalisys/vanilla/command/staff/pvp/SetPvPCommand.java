package it.mathanalisys.vanilla.command.staff.pvp;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetPvPCommand extends Command {

    public SetPvPCommand() {
        super("setpvp");
        setAliases(List.of("pvp"));
        setPermission("vanilla.command.setpvp");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if(!sender.hasPermission(getPermission())){
            sender.sendMessage(CC.translate("&cPermessi Insufficienti"));
            return true;
        }

        if (!sender.getName().equalsIgnoreCase("KillerGamerPls")){
            sender.sendMessage(CC.translate("&cNon sei il padrone brutto schiavo suddito non ci provare!"));
            return true;
        }



        if (args.length == 0){
            sender.sendMessage(CC.translate("&cUsage: /setpvp <on|off>"));
        }

        if (args[0].equalsIgnoreCase("on")){
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(!Vanilla.get().getPvPManager().isPvP()){
                    Vanilla.get().getPvPManager().setPvP(true, player);
                }
            });
            sender.sendMessage(CC.translate("&aHai appena attivato il &aPvP"));
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(CC.translate("&aHanno appena attivato il &lPvP"));
            });
        }

        if (args[0].equalsIgnoreCase("off")){
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(Vanilla.get().getPvPManager().isPvP()){
                    Vanilla.get().getPvPManager().setPvP(false, player);
                }
            });
            sender.sendMessage(CC.translate("&cHai appena disattivato il &lPvP"));
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(CC.translate("&cHanno appena disattivato il &lPvP"));
            });
        }


        return false;
    }
}
