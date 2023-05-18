package it.mathanalisys.vanilla.utils.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import it.mathanalisys.vanilla.Vanilla;

import java.util.concurrent.ThreadFactory;


public class Tasks {

    public static ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }

    public static void run(Callable callable) {
        Vanilla.get().getServer().getScheduler().runTask(Vanilla.get(), callable::call);
    }

    public static void runAsync(Callable callable) {
        Vanilla.get().getServer().getScheduler().runTaskAsynchronously(Vanilla.get(), callable::call);
    }

    public static void runLater(Callable callable, long delay) {
        Vanilla.get().getServer().getScheduler().runTaskLater(Vanilla.get(), callable::call, delay);
    }

    public static void runAsyncLater(Callable callable, long delay) {
        Vanilla.get().getServer().getScheduler().runTaskLaterAsynchronously(Vanilla.get(), callable::call, delay);
    }

    public static void runTimer(Callable callable, long delay, long interval) {
        Vanilla.get().getServer().getScheduler().runTaskTimer(Vanilla.get(), callable::call, delay, interval);
    }

    public static void runAsyncTimer(Callable callable, long delay, long interval) {
        Vanilla.get().getServer().getScheduler().runTaskTimerAsynchronously(Vanilla.get(), callable::call, delay, interval);
    }

    public interface Callable {
        void call();
    }
}
