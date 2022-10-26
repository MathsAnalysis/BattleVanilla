package it.mathanalisys.vanilla.utils.animations.text;

import it.mathanalisys.vanilla.utils.animations.TextAnimation;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class TypewriterAnimation extends TextAnimation {

    public TypewriterAnimation() {
        super("typewriter", 3, 20);
    }

    @Override
    public String animate(String string, long step, String... args) {
        StringBuilder specialColors = new StringBuilder();
        String stripped = ChatColor.stripColor(string);
        int currentStep = getCurrentStep(step, stripped.length());
        return specialColors + String.valueOf(Arrays.copyOfRange(stripped.toCharArray(), 0, currentStep));
    }
}
