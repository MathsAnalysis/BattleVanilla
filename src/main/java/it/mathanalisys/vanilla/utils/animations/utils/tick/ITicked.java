package it.mathanalisys.vanilla.utils.animations.utils.tick;

import it.mathanalisys.vanilla.Vanilla;

public interface ITicked {

    String getId();

    void tick();

    long getInterval();

    default boolean shouldTick(long tick) {
        return tick % getInterval() == 0;
    }

    default void register() {
        Vanilla.get().getTicker().register(this);
    }

    default void unregister() {
        Vanilla.get().getTicker().unregister(getId());
    }

}
