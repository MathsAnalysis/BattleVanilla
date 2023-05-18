package it.mathanalisys.vanilla.backend;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter
@Setter
public  class DatabaseManager {

    public MongoClient client;
    public MongoDatabase database;
    public MongoCollection<Document> players;

    public DatabaseManager(){
        this.client = new MongoClient("localhost", 27017);
        this.database = this.client.getDatabase("vanilla");
        this.players = this.database.getCollection("players");
    }
}