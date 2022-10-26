package it.mathanalisys.vanilla.utils.animations.utils;

import it.mathanalisys.vanilla.utils.animations.utils.collection.AnimationList;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimationExecutor {

    private static boolean initialized = false;
    private static ExecutorService service;
    private static int threadId;

    /**
     * Initialize DExecutor. This method will set up ExecutorService for DecentHolograms.
     *
     * @param threads The amount of threads in the service.
     */
    public static void init(int threads) {
        if (initialized) return;
        threadId = 0;
        service = Executors.newFixedThreadPool(threads, (runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setName("DecentHolograms Thread #" + ++threadId);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.setUncaughtExceptionHandler((t, ex) -> {
                System.out.println("Exception encountered in " + t.getName());
                ex.printStackTrace();
            });
            return thread;
        });
        initialized = true;
    }

    /**
     * Execute given runnables using the ExecutorService.
     *
     * @param runnables the runnables.
     */
    public static void schedule(Runnable... runnables) {
        if (!initialized) return;
        if (runnables == null || runnables.length == 0) {
            return;
        }
        create(runnables.length).queue(runnables).complete();
    }

    /**
     * Create new instance of DExecutor that handles scheduled runnables.
     *
     * @param estimate The estimated amount of runnables.
     * @return The new instance.
     */
    public static AnimationExecutor create(int estimate) {
        if (!initialized) return null;
        return new AnimationExecutor(service, estimate);
    }

    /**
     * Execute a runnable using the ExecutorService.
     *
     * @param runnable The runnable.
     */
    public static void execute(Runnable runnable) {
        service.execute(runnable);
    }

    private final ExecutorService executor;
    private final AnimationList<CompletableFuture<Void>> running;

    AnimationExecutor(ExecutorService executor, int estimate) {
        this.executor = executor;
        this.running = new AnimationList<>(estimate);
    }

    /**
     * Schedule a new runnable.
     *
     * @param r The runnable.
     * @return CompletableFuture executing the runnable.
     */
    public CompletableFuture<Void> queue(Runnable r) {
        synchronized(running) {
            CompletableFuture<Void> c = CompletableFuture.runAsync(r, executor);
            running.add(c);
            return c;
        }
    }

    /**
     * Schedule more runnables.
     *
     * @param runnables The runnables.
     * @return This instance. (For chaining)
     */
    public AnimationExecutor queue(Runnable... runnables) {
        if (runnables == null || runnables.length == 0) {
            return this;
        }

        synchronized (running) {
            for (Runnable runnable : runnables) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(runnable, executor);
                running.add(future);
            }
        }
        return this;
    }

    /**
     * Complete all scheduled runnables.
     */
    public void complete() {
        synchronized(running) {
            try {
                CompletableFuture.allOf(running.toArray(new CompletableFuture[0])).get();
                running.clear();
            } catch(InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
