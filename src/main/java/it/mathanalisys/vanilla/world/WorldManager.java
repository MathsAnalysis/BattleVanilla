package it.mathanalisys.vanilla.world;

import it.mathanalisys.vanilla.Vanilla;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

public class WorldManager {

    @Getter
    @Setter
    private boolean day = false;

    public WorldManager(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Vanilla.get(), new WorldRunnable(), 0L, 1L);
    }
}
