package it.mathanalisys.vanilla.listener;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.luckperms.LuckPermsUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class GeneralListener implements Listener {

    @EventHandler
    public void onChatting(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        LuckPermsUtils.getPrefix(player);
        event.setFormat(CC.translate(LuckPermsUtils.getRankColor(player) + player.getName() + " &8» &7" + event.getMessage()));
    }

    @EventHandler
    public void setPvPOnServer(EntityDamageByEntityEvent event) {
        if (!Vanilla.get().getPvPManager().isPvP()){
            if (event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER) {
                event.setCancelled(true);
            }
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onShootPlayer(ProjectileHitEvent event) {
        if (!Vanilla.get().getPvPManager().isPvP()){
            if ((event.getEntity().getType() == EntityType.PLAYER) && (event.getEntity().getShooter() instanceof Player)) {
                event.setCancelled(true);
            }
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerAsyncConnection(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.hasWhitelist()) {
            TextComponent message = Component.text(CC.translate(
                            "&4Tu non puoi accedere a questo server è privato :(\n" +
                            "&4Attento che chiamo il 118 eheheh!\n" +
                            "4Oh davvero guardo che lo chiamo"
            ));


            boolean isWhitelisted = Bukkit.getWhitelistedPlayers().contains(player);


            if (!isWhitelisted) {
                event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, message.content());
            } else {
                event.allow();
            }

        }
    }
}
