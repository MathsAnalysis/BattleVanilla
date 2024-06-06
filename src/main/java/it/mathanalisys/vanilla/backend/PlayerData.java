package it.mathanalisys.vanilla.backend;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import it.mathanalisys.vanilla.Vanilla;
import lombok.Data;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Data
@SuppressWarnings("all")
public class PlayerData {

    public static HashMap<UUID, PlayerData> datas = new HashMap<>();

    private boolean loaded;

    private UUID uuid;
    private String name;

    private LocalDateTime localDateTime;

    private int kills, deaths, mobKills, blockBroken;

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
        CompletableFuture.runAsync(this::loadData).exceptionallyAsync((e)-> {
            e.printStackTrace();
            return null;
        });
    }

    public void loadData() {
        Document document = Vanilla.get().getDatabaseManager().getPlayers().find(Filters.eq("_id", this.uuid.toString())).first();

        if (document == null){
            name = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
            this.save();
            return;
        }

        this.name = document.getString("name");
        this.mobKills = document.getInteger("mobKills");
        this.kills = document.getInteger("kills");
        this.deaths = document.getInteger("deaths");
        this.blockBroken = document.getInteger("blockBroken");
        this.localDateTime = LocalDateTime.parse(document.getString("localDateTime"));
    }

    public void save(){
        CompletableFuture.runAsync(this::saveData).exceptionallyAsync((e)-> {
            e.printStackTrace();
            return null;
        });
    }

    protected void saveData(){
        Vanilla.get().getDatabaseManager().getPlayers().replaceOne(Filters.eq("_id", this.uuid.toString()), toDocument(), new ReplaceOptions().upsert(true));
    }


    public Document toDocument(){
        return new Document()
                .append("uuid", this.uuid.toString())
                .append("name", this.name)
                .append("mobKills", this.mobKills)
                .append("kills", this.kills)
                .append("deaths", this.deaths)
                .append("localDateTime", this.localDateTime.toString())
                .append("blockBroken", this.blockBroken);
    }


    public static CompletableFuture<PlayerData> getData(String name){
        return CompletableFuture.supplyAsync(()-> {
            PlayerData playerData = datas.values().stream().filter(data -> data.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

            if(playerData == null){
                Document document = Vanilla.get().getDatabaseManager().getPlayers().find(Filters.eq("name", name)).first();

                if(document == null){
                    return null;
                }

                playerData = new PlayerData(UUID.fromString(document.getString("_id")), document.getString("name"));
            }

            return playerData;
        });
    }

    public static CompletableFuture<PlayerData> getData(UUID uuid) {
        return CompletableFuture.supplyAsync(()-> {
            PlayerData playerData = datas.get(uuid);

            if(playerData == null){
                playerData = new PlayerData(uuid);
            }

            return playerData;
        });
    }

    public static HashMap<UUID, PlayerData> getDatas() {
        return datas;
    }
}