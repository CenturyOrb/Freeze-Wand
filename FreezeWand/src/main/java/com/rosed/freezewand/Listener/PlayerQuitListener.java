package com.rosed.freezewand.Listener;

import com.rosed.freezewand.Profile.PlayerProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerQuitListener implements Listener {

    private PlayerProfileManager playerProfileManager;

    public PlayerQuitListener(PlayerProfileManager playerProfileManager)   {

        this.playerProfileManager = playerProfileManager;

    }

    /**
     * when a player leaves the server, remove hashmap set relative to the player
     * @param e PlayerQuitEvent
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)   {

        Player player = e.getPlayer();

        playerProfileManager.getPlayerProfiles().remove(player.getUniqueId());

    }

}
