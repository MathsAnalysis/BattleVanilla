package it.mathanalisys.vanilla.backend;

import com.mongodb.client.model.Filters;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.thread.Tasks;
import lombok.Data;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

@Data
@SuppressWarnings("all")
public class PlayerData {

    public static HashMap<UUID, PlayerData> datas = new HashMap<>();

    private boolean loaded;

    private UUID uuid;
    private String name;

    private int kills, deaths,
            mobKills, blockBroken,
            goldenAppleEaten;

    public PlayerData(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        datas.put(this.uuid, this);
        this.load(true);
    }

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
        datas.put(this.uuid, this);
    }

    public void load(boolean async) {
        if(async) {
            Tasks.runAsync(()-> loadData());
        } else {
            loadData();
        }
    }

    public void loadData() {
        Document document = Vanilla.get().getDatabaseManager().getPlayers().find(Filters.eq("uuid", this.uuid.toString())).first();
        if (document != null) {
            if (this.name == null) {
                this.name = document.getString("name");
            }
            this.mobKills = document.getInteger("mobKills");
            this.kills = document.getInteger("kills");
            this.deaths = document.getInteger("deaths");
            this.blockBroken = document.getInteger("blockBroken");
        }else {
            Vanilla.get().getDatabaseManager().getPlayers().insertOne(new Document()
                    .append("uuid", this.uuid.toString())
                    .append("name", this.name != null ? this.name : Bukkit.getPlayer(this.uuid) == null ? Bukkit.getOfflinePlayer(this.uuid).getName() : Bukkit.getPlayer(this.uuid).getName())
                    .append("mobKills", this.mobKills)
                    .append("kills", this.kills)
                    .append("deaths", this.deaths)
                    .append("blockBroken", this.blockBroken)
            );
        }
        this.loaded = true;
    }

    public static PlayerData getByName(String name) {
        return getByUuid(Bukkit.getPlayer(name) == null
                ? Bukkit.getOfflinePlayer(name).getUniqueId()
                : Bukkit.getPlayer(name).getUniqueId());
    }

    public static PlayerData getByUuid(UUID uuid) {
        PlayerData data = datas.get(uuid);

        if (data == null) {
            data = new PlayerData(uuid);
            data.load(true);
        }

        return data;
    }

    public static HashMap<UUID, PlayerData> getDatas() {
        return datas;
    }
}