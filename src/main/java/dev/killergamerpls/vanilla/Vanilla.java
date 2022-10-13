package dev.killergamerpls.vanilla;

import dev.killergamerpls.vanilla.backend.DatabaseManager;
import dev.killergamerpls.vanilla.command.LeaderboardCommand;
import dev.killergamerpls.vanilla.command.PingCommand;
import dev.killergamerpls.vanilla.command.StatsCommand;
import dev.killergamerpls.vanilla.command.staff.WhitelistCommand;
import dev.killergamerpls.vanilla.command.staff.pvp.PvPManager;
import dev.killergamerpls.vanilla.command.staff.pvp.SetPvPCommand;
import dev.killergamerpls.vanilla.command.staff.velocity.VelocityCommand;
import dev.killergamerpls.vanilla.leaderboard.LeaderboardManager;
import dev.killergamerpls.vanilla.listener.DataListener;
import dev.killergamerpls.vanilla.listener.GeneralListener;
import dev.killergamerpls.vanilla.provider.AdapterManager;
import dev.killergamerpls.vanilla.thread.ReduceLagThread;
import dev.killergamerpls.vanilla.utils.animations.AnimationManager;
import dev.killergamerpls.vanilla.utils.animations.utils.AnimationExecutor;
import dev.killergamerpls.vanilla.utils.animations.utils.tick.Ticker;
import dev.killergamerpls.vanilla.world.WorldManager;
import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
@Setter
public class Vanilla extends JavaPlugin {

    @Getter
    private static Vanilla instance;
    private DatabaseManager databaseManager;
    private LeaderboardManager leaderboardManager;
    private WorldManager worldManager;
    private AdapterManager adapterManager;
    private PvPManager pvPManager;
    private ReduceLagThread reduceLagThread;
    private InventoryManager inventoryManager;


    private AnimationManager animationManager;
    private Ticker ticker;


    @Override
    public void onEnable() {
        instance = this;

        loadDatabase();
        loadManager();
        loadListener();
        loadOther();
    }

    private void loadManager(){
        this.leaderboardManager = new LeaderboardManager(this);
        this.worldManager = new WorldManager();
        this.adapterManager = new AdapterManager();
        this.pvPManager = new PvPManager();

        //Inv Function
        this.inventoryManager = new InventoryManager(this);
        this.inventoryManager.init();
    }

    private void loadOther(){
        Bukkit.getCommandMap().register(getName(), new SetPvPCommand());
        List.of(
                new StatsCommand(),
                new LeaderboardCommand(),
                new WhitelistCommand(),
                new PingCommand(),
                new SetPvPCommand(),
                new VelocityCommand()
        ).forEach(command-> Bukkit.getCommandMap().register(getName(), command));
        AnimationExecutor.init(Runtime.getRuntime().availableProcessors());

        //Animation Text
        AnimationExecutor.init(Runtime.getRuntime().availableProcessors());
        this.ticker = new Ticker();
        this.animationManager = new AnimationManager();

        //todo load thread for tick rage reduce, warning to place sleep thread
        /*reduceLagThread = new ReduceLagThread();
        int tick = Bukkit.getServer().getTPS().length;
        if (tick <= 19){
            reduceLagThread.start();
        }else {
            reduceLagThread.interrupt();
        }*/
    }

    private void loadListener(){
        List.of(
                new DataListener(),
                new GeneralListener()
        ).forEach(listener-> Bukkit.getServer().getPluginManager().registerEvents(listener, this));
    }

    private void loadDatabase(){
        this.databaseManager = new DatabaseManager();
        this.databaseManager.startDatabase();
    }

    public static Vanilla get(){
        return instance;
    }
}
