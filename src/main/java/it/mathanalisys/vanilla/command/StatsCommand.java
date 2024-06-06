package it.mathanalisys.vanilla.command;

import com.mongodb.client.model.Filters;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.backend.PlayerData;
import it.mathanalisys.vanilla.utils.CC;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StatsCommand extends Command {

    public StatsCommand() {
        super("stats");
        setAliases(List.of("statistiche"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0){
            if (sender instanceof Player player){
                PlayerData.getData(player.getUniqueId()).thenAcceptAsync(playerData -> {
                    player.sendMessage(CC.translate(
                            """
                            &9&lStatistiche
                            &c&d&3
                            Uccisioni: %s
                            Morti: %s
                            Mob Uccisi: %s
                            Blocchi Rotti: %s
                            """.formatted(
                                    playerData.getKills(),
                                    playerData.getDeaths(),
                                    playerData.getMobKills(),
                                    playerData.getBlockBroken()
                            )
                    ));
                }).exceptionallyAsync(throwable -> {
                    player.sendMessage(CC.translate("&cStatistiche non trovate!"));
                    return null;
                });
                return true;
            }
            return true;
        }


        CompletableFuture.runAsync(()->{
            Document document = Vanilla.get().getDatabaseManager().getPlayers().find(Filters.eq("name", args[0])).first();
            if (document == null){
                sender.sendMessage(CC.translate("&CStatistiche non trovate di &l" + args[0] + "&c!"));
                return;
            }

            PlayerData.getData(document.getString("name")).thenAcceptAsync(playerData ->{
                sender.sendMessage(CC.translate(
                        """
                        &9&lStatistiche
                        &c&d&3
                        Uccisioni: %s
                        Morti: %s
                        Mob Uccisi: %s
                        Blocchi Rotti: %s
                        """.formatted(
                                playerData.getKills(),
                                playerData.getDeaths(),
                                playerData.getMobKills(),
                                playerData.getBlockBroken()
                        )
                ));
            }).exceptionallyAsync(throwable ->{
                sender.sendMessage(CC.translate("&cStatistiche non trovate!"));
                return null;
            });
        });

        return false;
    }
}
