package it.mathanalisys.vanilla.listener;

import com.mongodb.lang.Nullable;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.DatabaseUtils;
import it.mathanalisys.vanilla.backend.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.thread.Tasks;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        event.joinMessage(null);

        player.sendMessage("");
        player.sendMessage(CC.translate("&7Hey, ciao benvenuto nella mia Vanilla &d" + player.getName()));
        player.sendMessage("");

        if(Bukkit.getOnlinePlayers().size() > 0){
            Bukkit.getServer().broadcast(Component.text(CC.translate("&7Date il benvenuto al giocatore &d" + player.getName())));
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.quitMessage(null);
        PlayerData.getDatas().remove(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.deathMessage(null);
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
        playerData.setDeaths(playerData.getDeaths() + 1);
        Tasks.runAsync(()-> DatabaseUtils.updateStats(playerData.getUuid(), "deaths", playerData.getDeaths()));

        if (killer != null) {
            PlayerData killerData = PlayerData.getByUuid(player.getUniqueId());
            killerData.setKills(killerData.getKills() + 1);
            Tasks.runAsync(() -> DatabaseUtils.updateStats(killerData.getUuid(), "kills", killerData.getKills()));

            event.deathMessage(Component.text(CC.translate(Color.GREEN + player.getName() + " &7è stato ucciso nelle grinfie di &c" + killer.getName())));
        } else {event.deathMessage(Component.text(CC.translate(Color.PURPLE + player.getName() + " &7è morto per cause sconosciute!")));}


    }

    @EventHandler
    public void onPlayerKillMob(EntityDeathEvent event) {
        Entity eventEntity = event.getEntity();
        if (eventEntity.getLastDamageCause() instanceof EntityDamageByEntityEvent entityDamageByEntityEvent) {
            if (entityDamageByEntityEvent.getDamager() instanceof Player player) {
                PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
                playerData.setMobKills(playerData.getMobKills() + 1);
                Tasks.runAsync(()-> DatabaseUtils.updateStats(playerData.getUuid(), "mobKills", playerData.getMobKills()));
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());
        playerData.setBlockBroken(playerData.getBlockBroken() + 1);
        Tasks.runAsync(()-> DatabaseUtils.updateStats(playerData.getUuid(), "blockBroken", playerData.getBlockBroken()));
    }


}
