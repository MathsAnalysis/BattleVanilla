package it.mathanalisys.vanilla.provider;

import it.mathanalisys.vanilla.utils.luckperms.LuckPermsUtils;
import it.mathanalisys.vanilla.utils.nametag.NameTagHandler;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class NametagsAdapter extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(toRefresh -> Bukkit.getOnlinePlayers().forEach(refreshFor -> {
            NameTagHandler.color(refreshFor, toRefresh, LuckPermsUtils.getRankColor(refreshFor), true);
        }));
    }
}