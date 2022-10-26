package dev.killergamerpls.vanilla.backend;

import dev.killergamerpls.vanilla.backend.data.PlayerData;
import dev.killergamerpls.vanilla.backend.sql.SQLInterface;
import dev.killergamerpls.vanilla.utils.CC;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;

import java.sql.*;

public class DatabaseManager extends SQLInterface {

    private Connection connection;


    public DatabaseManager() {
        super("jdbc:mariadb://localhost/Vanilla", "root", "CarloBellissimo2001", 3306);
    }

    @SneakyThrows
    public Connection getConnection(){

        if(connection != null){
            return connection;
        }

        Connection staticConnection = DriverManager.getConnection(getDriverUrl(), getUser(), getPassword());

        if (this.connection == staticConnection){
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getConsoleSender().sendMessage(CC.translate("&a&lConnessione al database effettuata!"));
            Bukkit.getConsoleSender().sendMessage("");
        }
        return staticConnection;
    }

    @SneakyThrows
    public void startDatabase(){
        Statement statement = getConnection().createStatement();

        //Create table for mariadb
        String tableStatistics = "CREATE TABLE IF NOT EXISTS statistics(uuid varchar(48) primary key, name varchar(16), kills int, deaths int, mobKills int, blockBroken int, goldenAppleEaten int)";
        statement.execute(tableStatistics);
        statement.close();
    }

    @SneakyThrows
    public void createStatistics(PlayerData data){
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO statistics(uuid, name,  kills, deaths, mobKills, blockBroken, goldenAppleEaten) VALUES  (?,?,?,?,?,?,?)");
        statement.setString(1, data.getUuid());
        statement.setString(2, data.getName());
        statement.setInt(3, data.getKills());
        statement.setInt(4, data.getDeaths());
        statement.setInt(5, data.getMobKills());
        statement.setInt(6, data.getBlockBroken());
        statement.setInt(7, data.getGoldenAppleEaten());
        statement.executeUpdate();
        statement.close();
    }

    @SneakyThrows
    public void updateStatsPlayer(PlayerData data) {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE statistics SET kills = ?, deaths = ?, mobKills =?, blockBroken = ?, name = ? WHERE uuid = ?");
        statement.setInt(1, data.getKills());
        statement.setInt(2, data.getDeaths());
        statement.setInt(3, data.getMobKills());
        statement.setInt(4, data.getBlockBroken());
        statement.setInt(5, data.getGoldenAppleEaten());
        statement.setString(6, data.getName());
        statement.setString(7, data.getUuid());
        statement.executeUpdate();
        statement.close();
    }

    @SneakyThrows
    public PlayerData findPlayerStatsByUUID(String uuid)  {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM statistics WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        PlayerData playerStats = null;

        if(resultSet.next()){
            playerStats = new PlayerData(resultSet.getString("uuid"), resultSet.getString("name"), resultSet.getInt("kills"), resultSet.getInt("deaths"), resultSet.getInt("mobKills"), resultSet.getInt("blockBroken"), resultSet.getInt("goldenAppleEaten"));
            statement.close();
            return playerStats;
        }
        statement.close();
        return null;
    }


    @SneakyThrows
    public void deletePlayerStats(PlayerData playerData) {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM player_stats WHERE uuid = ?");
        statement.setString(1, playerData.getUuid());
        statement.executeUpdate();
        statement.close();

    }

}