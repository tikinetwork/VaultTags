package dev.foolen.vaulttags;

import dev.foolen.vaulttags.listeners.PlayerJoin;
import dev.foolen.vaulttags.modules.teams.TeamsModule;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

public final class VaultTagsPlugin extends JavaPlugin {

    private static VaultTagsPlugin instance;

    private static Permission perms = null;
    private static Chat chat = null;

    @Override
    public void onEnable() {
        instance = this;

        setupPermissions();
        setupChat();
        registerEvents();

        // Load modules
        new TeamsModule();
    }

    @Override
    public void onDisable() {
        TeamsModule.removeTeams();
    }

    public static VaultTagsPlugin getInstance() {
        return instance;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(), this);
    }
}
