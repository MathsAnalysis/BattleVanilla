package it.mathanalisys.vanilla.provider;

import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.luckperms.LuckPermsUtils;
import it.mathanalisys.vanilla.utils.netherboard.Netherboard;
import it.mathanalisys.vanilla.utils.netherboard.bukkit.BPlayerBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VanillaBoard extends BukkitRunnable {

    private BPlayerBoard bPlayerBoard;

    public void createScoreboardPlayer(Player player){
        bPlayerBoard = Netherboard.instance().getBoard(player);
        List<String> board = new ArrayList<>();

        if (bPlayerBoard == null) {
            bPlayerBoard = Netherboard.instance().createBoard(player, CC.translate("&5&lVANILLA"));
        }
        bPlayerBoard.setName(Vanilla.get().getAnimationManager().parseTextAnimations("<#ANIM:wave:&5&l,&d&l>VANILLA</#ANIM>"));

        ZoneId zoneId = ZoneId.of("Europe/Rome");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(zoneId);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        board.add("&6&8&m--------------------");
        board.add("&7&o" + formatter.format(date) + " " + dtf.format(now));
        board.add("&d&d&3&l");
        board.add("&dNome: &7" + player.getName());
        board.add("&dGiocatori Online: &7" + Bukkit.getServer().getOnlinePlayers().size());
        board.add("&C&d&4&3");
        board.add("&dLatenza: &7" + player.spigot().getPing() + "ms");
        board.add("&dRank: &7" + LuckPermsUtils.getPrefix(player));
        board.add("&C&d&3");
        board.add("&dCoordinate: &7" + x + ", " + y + ", " + z);
        board.add("&dOrario: &7" + (Vanilla.get().getWorldManager().isDay() ? "Giorno" : "Notte"));
        board.add("&8&m--------------------");



        for (int i = 0; i < board.size(); i++) {
            int pos = board.size() - i;
            bPlayerBoard.set(CC.translate(board.get(i)), pos);
        }
    }


    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            createScoreboardPlayer(player);
        }
    }
}
