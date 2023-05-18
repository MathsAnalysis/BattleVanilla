package it.mathanalisys.vanilla.backend;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import it.mathanalisys.vanilla.Vanilla;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.logging.Level;

public class DatabaseUtils {

    public static void updateStats(UUID uuid, String table, String type, Object value) {
        MongoCollection<Document> collection = Vanilla.get().getDatabaseManager().getDatabase().getCollection(table);
        Document document = collection.find(Filters.eq("uuid", uuid.toString())).first();

        if(document == null) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, "Cannot update stats for ID=" + uuid + "!");
            return;
        }

        document.put(type, value);

        collection.replaceOne(Filters.eq("uuid", uuid.toString()), document, new ReplaceOptions().upsert(true));
    }


    public static void updateStats(UUID id, String type, Object value) {
        updateStats(id, "players", type, value);
    }
}
