package dev.killergamerpls.vanilla.utils.animations.text;

import dev.killergamerpls.vanilla.utils.animations.TextAnimation;
import org.bukkit.ChatColor;

public class ScrollAnimation extends TextAnimation {

    public ScrollAnimation() {
        super("scroll", 3, 0);
    }

    @Override
    public String animate(String string, long step, String... args) {
        StringBuilder specialColors = new StringBuilder();
        String stripped = ChatColor.stripColor(string);
        int length = stripped.length();
        int size = length / 3 * 2;
        int currentStep = getCurrentStep(step, length);
        int index2 = currentStep + size;
        if (index2 > length) {
            return specialColors + stripped.substring(currentStep) + " " + specialColors + stripped.substring(0, index2 - length);
        }
        return specialColors + stripped.substring(currentStep, index2);
    }
}
