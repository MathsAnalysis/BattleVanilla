package dev.killergamerpls.vanilla.utils.animations;

public abstract class TextAnimation extends Animation {

    public TextAnimation(String name, int speed, int pause) {
        super(name, speed, pause);
    }

    public TextAnimation(String name, int speed, int pause, String... aliases) {
        super(name, speed, pause, aliases);
    }

    public abstract String animate(String string, long step, String... args);

}
