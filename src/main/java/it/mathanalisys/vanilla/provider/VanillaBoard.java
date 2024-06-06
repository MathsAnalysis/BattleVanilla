package it.mathanalisys.vanilla.provider;

import fr.mrmicky.fastboard.FastBoard;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class VanillaBoard extends BukkitRunnable {

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    @Override
    public void run() {
        this.boards.values().forEach(this::scoreboardUpdate);
    }

    public void scoreboardUpdate(FastBoard board){
        List<String> lines = Vanilla.get().getScoreboardConfig().getStringList("Scoreboard.Lines");
        board.updateLines(CC.translateStrings(board.getPlayer(), lines));
        board.updateTitle(CC.translate(board.getPlayer(), Vanilla.get().getScoreboardConfig().getString("Scoreboard.Title")));
    }

}
