package dev.killergamerpls.vanilla.world;

import dev.killergamerpls.vanilla.Vanilla;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

public class WorldManager {

    @Getter
    @Setter
    private boolean day = false;

    public WorldManager(){
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Vanilla.get(), new WorldRunnable(), 0L, 1L);
    }
}