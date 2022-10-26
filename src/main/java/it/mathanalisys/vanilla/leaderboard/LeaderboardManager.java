package it.mathanalisys.vanilla.leaderboard;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            reloadLeaderboard("goldenAppleEaten", "Golden Apple Eaten");


        }

        new BukkitRunnable() {
            @Override
            public void run() {
                switch (index++){
                    case 1 -> {
                       reloadLeaderboard("kills", "Top Uccisioni");
                    }
                    case 2 -> {
                       reloadLeaderboard("deaths", "Top Morti");
                    }
                    case 3 -> {
                       reloadLeaderboard("mobKills", "Top Mob Uccisi ");
                    }
                    case 4-> {
                        reloadLeaderboard("blockBroken", "Top Blocchi Rotti");
                    }
                    case 5->{
                        reloadLeaderboard("goldenAppleEaten", "Top Golden Apple Eaten");
                        index = 0;
                    }
                }
            }
        }.runTaskTimerAsynchronously(Vanilla.get(), 0L, 200L);

    }

    private void reloadLeaderboard(String type, String niceType) {
        this.leaderboards.remove(type);

        PreparedStatement statement = null;
        ResultSet result = null;

        AtomicInteger count = new AtomicInteger(1);
        this.leaderboards.putIfAbsent(type, new ArrayList<>());

        try {
            Connection connection = Vanilla.get().getDatabaseManager().getConnection();
            statement = connection.prepareStatement("SELECT name, " + type + " FROM " + "statistics" + " ORDER BY 2 DESC LIMIT " + 10);
            result = statement.executeQuery();

            int size = leaderboards.get(type).size();
            this.leaderboards.putIfAbsent(type, new ArrayList<>());
            while (result.next()) {
                this.leaderboards.get(type).add(CC.translate("&7" + count.getAndIncrement()
                        + ". &f" + result.getString("name")
                        + "&7" + " [" + result.getInt(type) + "]"));
            }
            for (int i = size + 1; i <= 10; i++) {
                leaderboards.get(type).add(CC.translate("&7" + i + ". " + ChatColor.WHITE + "Caricamento..."));
            }
        }catch (SQLException e){e.printStackTrace();}
    }


    public String Type(String niceType){
        return niceType;
    }

}