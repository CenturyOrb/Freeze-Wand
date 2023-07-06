package com.rosed.freezewand.Listener;

import com.rosed.freezewand.Profile.PlayerProfile;
import com.rosed.freezewand.Profile.PlayerProfileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private PlayerProfileManager playerProfileManager;

    public PlayerJoinListener(PlayerProfileManager playerProfileManager)   {
        this.playerProfileManager = playerProfileManager;
    }

    /**
     * adds player to the hashmap with a profile for the player
     * @param e PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoinCreateProfile(PlayerJoinEvent e)   {

        UUID playerUUID = e.getPlayer().getUniqueId();
        playerProfileManager.getPlayerProfiles().put(playerUUID, new PlayerProfile(playerUUID));

    }

}
