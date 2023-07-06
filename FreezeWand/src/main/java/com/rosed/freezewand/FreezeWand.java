package com.rosed.freezewand;

import com.rosed.freezewand.Command.FreezeWandCommand;
import com.rosed.freezewand.Listener.FreezeWandListener;
import com.rosed.freezewand.Listener.PlayerJoinListener;
import com.rosed.freezewand.Profile.PlayerProfile;
import com.rosed.freezewand.Profile.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class FreezeWand extends JavaPlugin {

    private PlayerProfileManager playerProfileManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getScheduler().runTaskLater(this, () -> {register();}, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * register events on plugin start
     */
    private void register()   {

        // initialize managers
        playerProfileManager = new PlayerProfileManager();

        profileCurrentPlayers();

        // register events
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(playerProfileManager), this);
        Bukkit.getPluginManager().registerEvents(new FreezeWandListener(playerProfileManager), this);

        // register commands
        getCommand("freezewand").setExecutor(new FreezeWandCommand());
    }

    /**
     * in case of server reload add back current players before reload
     */
    private void profileCurrentPlayers()   {
        for (Player player : Bukkit.getOnlinePlayers())   {
            UUID playerUUID = player.getUniqueId();
            playerProfileManager.getPlayerProfiles().put(playerUUID, new PlayerProfile(playerUUID));
        }
    }
}
