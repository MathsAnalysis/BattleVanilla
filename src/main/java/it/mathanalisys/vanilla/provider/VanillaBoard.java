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
            bPlayerBoard = Netherboard.instance().createBoard(player, CC.translate("&4&lVANILLA"));
        }
        bPlayerBoard.setName(Vanilla.get().getAnimationManager().parseTextAnimations("<#ANIM:wave:&4&l,&c&l>VANILLA</#ANIM>"));

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
        board.add("&cNome: &f" + player.getName());
        board.add("&cGiocatori Online: &f" + Bukkit.getServer().getOnlinePlayers().size());
        board.add("&C&d&4&3");
        board.add("&cLatenza: &f" + player.spigot().getPing() + "ms");
        board.add("&cRank: &f" + LuckPermsUtils.getPrefix(player));
        board.add("&C&d&3");
        board.add("&cCoordinate: &f" + x + ", " + y + ", " + z);
        board.add("&cOrario: &f" + (Vanilla.get().getWorldManager().isDay() ? "Giorno" : "Notte"));
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
