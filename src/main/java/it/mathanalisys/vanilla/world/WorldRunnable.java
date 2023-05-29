package it.mathanalisys.vanilla.world;

import it.mathanalisys.vanilla.Vanilla;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldRunnable implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Bukkit.getOnlinePlayers().size() == 0) {
                return;
            }

            World world = Bukkit.getWorld(player.getWorld().getName());
            if (world == null) {
                return;
            }

            if (world.getTime() < 13000 && !Vanilla.get().getWorldManager().isDay()) {
                Vanilla.get().getWorldManager().setDay(true);
            } else if (world.getTime() >= 13000 && Vanilla.get().getWorldManager().isDay()) {
                Vanilla.get().getWorldManager().setDay(false);
            }
        }
    }
}
