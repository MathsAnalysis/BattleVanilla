package dev.killergamerpls.vanilla.utils.animations.text;

import dev.killergamerpls.vanilla.utils.animations.TextAnimation;
import org.bukkit.ChatColor;

public class BurnAnimation extends TextAnimation {

    public BurnAnimation() {
        super("burn", 2, 40);
    }

    @Override
    public String animate(String string, long step, String... args) {
        StringBuilder specialColors = new StringBuilder();
        String stripped = ChatColor.stripColor(string);
        int currentStep = getCurrentStep(step, stripped.length());
        String start = stripped.substring(0, currentStep);
        String end = stripped.substring(currentStep);
        return args[1] + specialColors + start + args[0] + specialColors + end;
    }
}
