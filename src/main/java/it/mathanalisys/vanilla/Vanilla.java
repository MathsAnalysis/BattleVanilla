package it.mathanalisys.vanilla;

import it.mathanalisys.vanilla.backend.DatabaseManager;
import it.mathanalisys.vanilla.command.LeaderboardCommand;
import it.mathanalisys.vanilla.command.PingCommand;
import it.mathanalisys.vanilla.command.StatsCommand;
import dev.killergamerpls.vanilla.command.staff.*;
import it.mathanalisys.vanilla.command.staff.flyboost.FlySpeedCommand;
import it.mathanalisys.vanilla.command.staff.pvp.PvPManager;
import it.mathanalisys.vanilla.command.staff.pvp.SetPvPCommand;
import it.mathanalisys.vanilla.leaderboard.LeaderboardManager;
import it.mathanalisys.vanilla.listener.DataListener;
import it.mathanalisys.vanilla.listener.GeneralListener;
import it.mathanalisys.vanilla.provider.AdapterManager;
import it.mathanalisys.vanilla.thread.ReduceLagThread;
import it.mathanalisys.vanilla.utils.animations.AnimationManager;
import it.mathanalisys.vanilla.utils.animations.utils.AnimationExecutor;
import it.mathanalisys.vanilla.utils.animations.utils.tick.Ticker;
import it.mathanalisys.vanilla.world.WorldManager;
import fr.minuskube.inv.InventoryManager;
import it.mathanalisys.vanilla.command.staff.*;
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
                new TrollCommand(),
                new GamemodeCommand(),
                new SetPvPCommand(),
                new FlySpeedCommand(),
                new RestrictCommand(),
                new LookUpCommand(),
                new ResetStatsCommand()
        ).forEach(command-> Bukkit.getCommandMap().register(getName(), command));
        AnimationExecutor.init(Runtime.getRuntime().availableProcessors());

        //Animation Text
        AnimationExecutor.init(Runtime.getRuntime().availableProcessors());
        this.ticker = new Ticker();
        this.animationManager = new AnimationManager();

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
