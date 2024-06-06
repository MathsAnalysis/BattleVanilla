package it.mathanalisys.vanilla.utils;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConfigFile extends YamlConfiguration {

    private final File file;

    public ConfigFile(JavaPlugin plugin, String name) {
        this.file = new File(plugin.getDataFolder(), name);

        if (!this.file.exists()) {
            plugin.saveResource(name, false);
        }

        try {
            this.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            this.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void set(@NotNull String path, @NotNull Object value) {
        super.set(path, value);
        this.save();
    }

    public void setIfNotExists(@NotNull String path, @NotNull Object value) {
        if (!this.contains(path)) {
            this.set(path, value);
        }
    }

    public void setDefault(@NotNull String path, @NotNull Object value) {
        if (!this.contains(path)) {
            this.set(path, value);
        }
    }

    public void remove(@NotNull String path) {
        super.set(path, null);
        this.save();
    }

    public void removeIfPresent(@NotNull String path) {
        if (this.contains(path)) {
            this.remove(path);
        }
    }

    public void removeDefault(@NotNull String path) {
        if (this.contains(path)) {
            this.remove(path);
        }
    }

    @Override
    public int getInt(@NotNull String path) {
        return super.getInt(path, 0);
    }

    @Override
    public double getDouble(@NotNull String path) {
        return super.getDouble(path, 0.0);
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return super.getBoolean(path, false);
    }

    @Override
    public String getString(@NotNull String path) {
        return CC.translate(super.getString(path, ""));
    }

    @Override
    public @NotNull List<String> getStringList(@NotNull String path) {
        return super.getStringList(path).stream().map(CC::translate).collect(Collectors.toList());
    }
}