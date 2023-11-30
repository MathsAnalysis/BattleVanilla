package it.mathanalisys.vanilla.utils.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import it.mathanalisys.vanilla.Vanilla;
import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadFactory;

@UtilityClass
public class Tasks {

    public ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }

    public void run(Callable callable) {
        Vanilla.get().getServer().getScheduler().runTask(Vanilla.get(), callable::call);
    }

    public void runAsync(Callable callable) {
        Vanilla.get().getServer().getScheduler().runTaskAsynchronously(Vanilla.get(), callable::call);
    }

    public void runLater(Callable callable, long delay) {
        Vanilla.get().getServer().getScheduler().runTaskLater(Vanilla.get(), callable::call, delay);
    }

    public void runAsyncLater(Callable callable, long delay) {
        Vanilla.get().getServer().getScheduler().runTaskLaterAsynchronously(Vanilla.get(), callable::call, delay);
    }

    public void runTimer(Callable callable, long delay, long interval) {
        Vanilla.get().getServer().getScheduler().runTaskTimer(Vanilla.get(), callable::call, delay, interval);
    }

    public void runAsyncTimer(Callable callable, long delay, long interval) {
        Vanilla.get().getServer().getScheduler().runTaskTimerAsynchronously(Vanilla.get(), callable::call, delay, interval);
    }

    public interface Callable {
        void call();
    }
}
