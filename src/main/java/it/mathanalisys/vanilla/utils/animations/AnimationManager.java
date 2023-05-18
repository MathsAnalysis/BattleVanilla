package it.mathanalisys.vanilla.utils.animations;

import it.mathanalisys.vanilla.utils.CC;
import it.mathanalisys.vanilla.utils.animations.text.*;
import it.mathanalisys.vanilla.utils.animations.utils.tick.Ticked;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimationManager extends Ticked {

    private static final Pattern ANIMATION_PATTERN = Pattern.compile("[<{]#?ANIM:(\\w+)(:\\S+)?[}>](.*?)[<{]/#?ANIM[}>]");
    private final Map<String, TextAnimation> animationMap = new HashMap<>();
    private final AtomicLong step;

    public AnimationManager() {
        super(1L);
        this.step = new AtomicLong(0);
        this.reload();
    }

    @Override
    public void tick() {
        step.incrementAndGet();
    }

    public void destroy() {
        this.unregister();
        this.animationMap.clear();
    }

    public void reload() {
        this.animationMap.clear();
        this.registerAnimation(new TypewriterAnimation());
        this.registerAnimation(new WaveAnimation());
        this.registerAnimation(new BurnAnimation());
        this.registerAnimation(new ScrollAnimation());
        this.registerAnimation(new ColorsAnimation());
        this.step.set(0);
        this.register();
    }

    public long getStep() {
        return step.get();
    }

    public String parseTextAnimations(String string) {
        Validate.notNull(string);

        Matcher matcher = ANIMATION_PATTERN.matcher(string);
        while (matcher.find()) {
            String animationName = matcher.group(1);
            String args = matcher.group(2);
            String text = matcher.group(3);

            TextAnimation animation = getAnimation(animationName);
            if (animation != null) {
                string = string.replace(matcher.group(), animation.animate(text, getStep(), args == null ? null : args.substring(1).split(",")));
                matcher = ANIMATION_PATTERN.matcher(string);
            }
        }

        if (string.contains("&u")) {
            TextAnimation animation = getAnimation("colors");
            if (animation != null) {
                string = string.replace("&u", animation.animate("", getStep()));
            }
        }

        return CC.translate(string);
    }

    public boolean containsAnimations(String string) {
        Matcher matcher = ANIMATION_PATTERN.matcher(string);
        return matcher.find() || string.contains("&u");
    }

    public TextAnimation registerAnimation(String name, TextAnimation animation) {
        return this.animationMap.put(name, animation);
    }

    public TextAnimation registerAnimation(TextAnimation animation) {
        return this.animationMap.put(animation.getName(), animation);
    }

    public TextAnimation unregisterAnimation(String name) {
        return animationMap.remove(name);
    }

    public TextAnimation getAnimation(String name) {
        return animationMap.get(name);
    }

}
