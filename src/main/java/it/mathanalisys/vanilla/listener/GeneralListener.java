package it.mathanalisys.vanilla.listener;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.luckperms.LuckPermsUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class GeneralListener implements Listener {

    @EventHandler
    public void onChatting(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        LuckPermsUtils.getPrefix(player);
        event.setFormat(CC.translate(" " + LuckPermsUtils.getRankColor(player) + player.getName() + " &8» &7" + event.getMessage()));
    }

    @EventHandler
    public void setPvPOnServer(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            event.setCancelled(!Vanilla.get().getPvPManager().isPvP());
        }
    }

    @EventHandler
    public void onPlayerAsyncConnection(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.hasWhitelist()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append("&4Tu non puoi accedere a questo server è privato :(\n")
                    .append("&4Attento che chiamo il 118 eheheh!\n")
                    .append("4Oh davvero guardo che lo chiamo");
            if (!player.isWhitelisted()) {
                event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, Component.text(CC.translate(stringBuilder.toString())));
            } else {
                event.allow();
            }
        }
    }
}
