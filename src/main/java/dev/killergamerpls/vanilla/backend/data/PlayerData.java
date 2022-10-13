package dev.killergamerpls.vanilla.backend.data;

import dev.killergamerpls.vanilla.Vanilla;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;

@Getter
@Setter
public class PlayerData {

    @Getter
    private String name, uuid;
    private int kills, deaths, mobKills, blockBroken,goldenAppleEaten;

    public PlayerData(String uuid, String name, Integer kills, Integer deaths, Integer mobKills, Integer blockBroken, Integer goldenAppleEaten){
        this.uuid = uuid;
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.mobKills = mobKills;
        this.blockBroken = blockBroken;
        this.goldenAppleEaten = goldenAppleEaten;
    }

    @SneakyThrows
    public static PlayerData getPlayerConnection(Player player) {
        PlayerData playerStats = Vanilla.get().getDatabaseManager().findPlayerStatsByUUID(player.getUniqueId().toString());
        if (playerStats == null) {
            playerStats = new PlayerData(player.getUniqueId().toString(), player.getName(), 0, 0, 0, 0, 0 );
            Vanilla.get().getDatabaseManager().createStatistics(playerStats);
        }
        return playerStats;
    }


}