package uwubak.uwuchatmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uwubak.uwuchatmanager.Commands.ChatCommand;
import uwubak.uwuchatmanager.Events.ChatEvent;

public final class Uwu_ChatManager extends JavaPlugin {

    private static Uwu_ChatManager plugin;

    public boolean chat;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("chat").setExecutor(new ChatCommand(this));
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);

        Bukkit.getConsoleSender().sendMessage("[uwu-ChatManager] Plugin sie uruchamia..");
        Bukkit.getConsoleSender().sendMessage("[uwu-ChatManager] Ladowanie Konfiguracji..");


    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[uwu-ChatManager] Zapisywanie aktualnej konfiguracji pluginu..");
        Bukkit.getConsoleSender().sendMessage("[uwu-ChatManager] Plugin zostal wylaczony!");
    }

    public static Uwu_ChatManager getPlugin() {
        return plugin;
    }
}
