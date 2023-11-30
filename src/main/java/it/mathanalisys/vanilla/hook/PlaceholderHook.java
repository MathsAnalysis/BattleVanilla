package it.mathanalisys.vanilla.hook;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.PlayerData;
import it.mathanalisys.vanilla.utils.luckperms.LuckPermsUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "Vanilla";
    }

    @Override
    public @NotNull String getAuthor() {
        return Vanilla.get().getDescription().getAuthors().toString().replace("]", "").replace("[", "");
    }

    @Override
    public @NotNull String getVersion() {
        return Vanilla.get().getDescription().getVersion();
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        PlayerData playerData = PlayerData.getByUuid(player.getUniqueId());

        switch (identifier){
            case "online" -> {
                return String.valueOf(Vanilla.get().getServer().getOnlinePlayers().size());
            }
            case "max" -> {
                return String.valueOf(Vanilla.get().getServer().getMaxPlayers());
            }
            case "name" -> {
                return player.getName();
            }
            case "rank_color"->{
                return LuckPermsUtils.getRankColor(player);
            }
            case "rank_prefix"->{
                return LuckPermsUtils.getPrefix(player);
            }
            case "rank_suffix"->{
                return LuckPermsUtils.getSuffix(player);
            }
            case "broken_blocks"->{
                return String.valueOf(playerData.getBlockBroken());
            }
            case "golden_apple_eaten"->{
                return String.valueOf(playerData.getGoldenAppleEaten());
            }
            case "mob_kills"->{
                return String.valueOf(playerData.getMobKills());
            }
            case "player_kills"->{
                return String.valueOf(playerData.getKills());
            }
            case "deaths"->{
                return String.valueOf(playerData.getDeaths());
            }
        }

        return null;
    }
}
