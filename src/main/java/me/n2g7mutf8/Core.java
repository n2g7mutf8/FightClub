package me.n2g7mutf8;

import me.n2g7mutf8.Commands.*;
import me.n2g7mutf8.Kits.KitManager;
import me.n2g7mutf8.Kits.List.FighterKit;
import me.n2g7mutf8.Kits.List.MedusaKit;
import me.n2g7mutf8.Listener.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Core extends JavaPlugin {

    private static Core instance;
    public static Core getInstance() { return instance; }

    public FileConfiguration config;

    public final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        helperCommandsReCall();
        registerListeners();
        kitRegister();
        new KitManager();
        saveDefaultConfig();
        config = this.getConfig();
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    public void helperCommandsReCall() {
        new KitCommand().register();
        new TestCommand().register();
        new SpawnCommand().register();
        new SetSpawnCommand().register();
        new StatsCommand().register();
    }

    private void registerListeners() {
        Arrays.asList(
                new DropEvent(),
                new DeadEvent(),
                new QuitEvent(),
                new JoinEvent()
        ).forEach(listener -> pluginManager.registerEvents(listener, this));
    }

    private void kitRegister() {
        Arrays.asList(
                new FighterKit(),
                new MedusaKit()
        ).forEach(listener -> pluginManager.registerEvents(listener, this));
    }
}
