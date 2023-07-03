package it.mathanalisys.vanilla.provider;

import fr.mrmicky.fastboard.FastBoard;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class VanillaBoard extends BukkitRunnable {


    @Override
    public void run() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            FastBoard board = new FastBoard(player);
            scoreboardUpdate(board);
        });
    }

    public void scoreboardUpdate(FastBoard board){
        List<String> lines = Vanilla.get().getScoreboardConfig().getStringList("Scoreboard.Lines");
        board.updateLines(CC.translateStrings(lines));
        board.updateTitle(Vanilla.get().getScoreboardConfig().getString("Scoreboard.Title"));
    }
}
