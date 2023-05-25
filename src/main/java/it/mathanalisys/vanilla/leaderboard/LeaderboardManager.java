package it.mathanalisys.vanilla.leaderboard;

import com.mongodb.BasicDBObject;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class LeaderboardManager {

    private final Map<String, List<String>> leaderboards = new HashMap<>();
    public static int index = 0;
    private JavaPlugin plugin;

    public LeaderboardManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadLeaderboards();
    }

    private void loadLeaderboards(){
        if(Vanilla.get().getDatabaseManager() != null) {
            reloadLeaderboard("kills", "Uccisioni");
            reloadLeaderboard("deaths", "Morti");
            reloadLeaderboard("mobKills", "Mob Uccisi");
            reloadLeaderboard("blockBroken", "Blocchi Rotti");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                switch (index++){
                    case 1 -> reloadLeaderboard("kills", "Top Uccisioni");
                    case 2 -> reloadLeaderboard("deaths", "Top Morti");
                    case 3 -> reloadLeaderboard("mobKills", "Top Mob Uccisi ");
                    case 4-> {
                        reloadLeaderboard("blockBroken", "Top Blocchi Rotti");
                        index = 0;
                    }
                }
            }
        }.runTaskTimerAsynchronously(Vanilla.get(), 0L, 200L);

    }

    private void reloadLeaderboard(String type, String niceType) {
        this.leaderboards.remove(type);
        AtomicInteger count = new AtomicInteger(1);
        this.leaderboards.putIfAbsent(type, new ArrayList<>());
        List<Document> documents = Vanilla.get().getDatabaseManager().getPlayers().find().limit(10).sort(new BasicDBObject(type, (-1))).into(new ArrayList<>());
        int size = leaderboards.get(type).size();

        for (Document document : documents){
            this.leaderboards.get(type).add(CC.translate("&7" + count.getAndIncrement()
                    + ". &f" + document.getString("name")
                    + "&7" + " [" + document.getInteger(type) + "]"));
        }

        for (int i = size + 1; i <= 10; i++) {
            leaderboards.get(type).add(CC.translate("&7" + i + ". " + Color.WHITE + "Caricamento..."));
        }
    }


    public String Type(String niceType){
        return niceType;
    }

}