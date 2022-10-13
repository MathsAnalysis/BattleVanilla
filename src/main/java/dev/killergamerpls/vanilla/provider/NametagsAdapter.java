package dev.killergamerpls.vanilla.provider;

import dev.killergamerpls.vanilla.utils.luckperms.LuckPermsUtils;
import dev.killergamerpls.vanilla.utils.nametag.NameTagHandler;
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