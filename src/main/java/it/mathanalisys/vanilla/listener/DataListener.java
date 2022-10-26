package it.mathanalisys.vanilla.listener;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.data.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        PlayerData playerData = PlayerData.getPlayerConnection(player);
        Vanilla.get().getDatabaseManager().updateStatsPlayer(playerData);

        event.setJoinMessage(null);

        player.sendMessage("");
        player.sendMessage(CC.translate("&cHey, ciao benvenuto nella mia Vanilla &l" + player.getName()));
        player.sendMessage("");

        if(Bukkit.getOnlinePlayers().size() > 0){
            Bukkit.getServer().broadcastMessage(CC.translate("&4Date il bevenuto a &l" + player.getName()));
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        PlayerData playerData = PlayerData.getPlayerConnection(player);
        Vanilla.get().getDatabaseManager().updateStatsPlayer(playerData);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        PlayerData playerData = PlayerData.getPlayerConnection(player);
        playerData.setDeaths(playerData.getDeaths() + 1);
        Vanilla.get().getDatabaseManager().updateStatsPlayer(playerData);

        if (killer != null) {
            if (killer != player) {
                PlayerData killerData = PlayerData.getPlayerConnection(killer);
                killerData.setKills(killerData.getKills() + 1);
                Vanilla.get().getDatabaseManager().updateStatsPlayer(killerData);
                event.setDeathMessage(CC.translate(ChatColor.GREEN + player.getName() + " &7è stato ucciso da &c" + killer.getName()));
            }
        } else {event.setDeathMessage(CC.translate(ChatColor.RED + player.getName() + " &7è morto per cause sconosciute!"));}
    }

    @EventHandler
    public void onPlayerKillMob(EntityDeathEvent event) {
        Entity eventEntity = event.getEntity();
        if (eventEntity.getLastDamageCause() instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
            if (entityDamageByEntityEvent.getDamager() instanceof Player player) {
                PlayerData data = PlayerData.getPlayerConnection(player);
                data.setMobKills(data.getMobKills() + 1);
                Vanilla.get().getDatabaseManager().updateStatsPlayer(data);
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getPlayerConnection(player);
        playerData.setBlockBroken(playerData.getBlockBroken() + 1);
        Vanilla.get().getDatabaseManager().updateStatsPlayer(playerData);
    }


}
