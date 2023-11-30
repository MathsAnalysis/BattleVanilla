package it.mathanalisys.vanilla.listener;

import fr.mrmicky.fastboard.FastBoard;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.DatabaseUtils;
import it.mathanalisys.vanilla.backend.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.thread.Tasks;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        player.sendMessage("");
        player.sendMessage(CC.translate("&7Hey, ciao benvenuto nella mia Vanilla &d" + player.getName()));
        player.sendMessage("");

        if(!Bukkit.getOnlinePlayers().isEmpty()){
            Bukkit.getServer().broadcastMessage(CC.translate("&7Date il benvenuto al giocatore &d" + player.getName()));
        }

        FastBoard fastBoard = new FastBoard(player);
        Vanilla.get().getVanillaBoard().getBoards().put(player.getUniqueId(), fastBoard);
        fastBoard.updateTitle(CC.translate(Vanilla.get().getScoreboardConfig().getString("Scoreboard.Title")));

    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        PlayerData.getDatas().remove(player.getUniqueId());

        FastBoard board = Vanilla.get().getVanillaBoard().getBoards().remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
        playerData.setDeaths(playerData.getDeaths() + 1);
        Tasks.runAsync(()-> DatabaseUtils.updateStats(playerData.getUuid(), "deaths", playerData.getDeaths()));

        if (killer != null) {
            PlayerData killerData = PlayerData.getByUuid(player.getUniqueId());
            killerData.setKills(killerData.getKills() + 1);
            killerData.save(true);
            event.setDeathMessage(CC.translate(Color.GREEN + player.getName() + " &7è stato ucciso nelle grinfie di &c" + killer.getName()));
        } else {
            event.setDeathMessage(CC.translate("&7E' morto per cause naturali &d" + player.getName()));
        }


    }

    @EventHandler
    public void onPlayerKillMob(EntityDeathEvent event) {
        Entity eventEntity = event.getEntity();
        if (eventEntity.getLastDamageCause() instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
            if (entityDamageByEntityEvent.getDamager() instanceof Player player) {
                PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
                playerData.setMobKills(playerData.getMobKills() + 1);
                playerData.save(true);
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
        playerData.setBlockBroken(playerData.getBlockBroken() + 1);
        playerData.save(true);
    }


}
