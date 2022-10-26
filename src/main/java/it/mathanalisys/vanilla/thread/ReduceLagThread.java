package dev.killergamerpls.vanilla.thread;

import dev.killergamerpls.vanilla.utils.CC;
import org.bukkit.Bukkit;

public class ReduceLagThread extends Thread{

    @Override
    public void run() {
        Bukkit.getWorlds().stream().toList().forEach(world -> {
            world.getEntities().clear();
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.isOp() || player.hasPermission("vanilla.admin")){
                    player.sendMessage(CC.translate("&4&lSistema fallato rilevazione di tick ridotto!"));
                }
            });
        });

    }
}
