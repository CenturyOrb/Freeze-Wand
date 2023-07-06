package com.rosed.freezewand.Listener;

import com.rosed.freezewand.Profile.PlayerProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class FreezeWandListener implements Listener {

    private PlayerProfileManager playerProfileManager;

    public FreezeWandListener(PlayerProfileManager playerProfileManager)   {

        this.playerProfileManager = playerProfileManager;

    }

    /**
     * toggles freeze for a player entity if user has freeze wand
     * @param e PlayerInteractEntityEvent
     */
    @EventHandler
    public void onPlayerFreezeToggle(PlayerInteractEntityEvent e)   {

        if (!e.getHand().equals(EquipmentSlot.HAND))   { return; }

        Player player = e.getPlayer();

        // check if a Player is interacting with another player
        if (!(e.getRightClicked() instanceof Player))   { return; }
        UUID rightClickedPlayerUUID =  e.getRightClicked().getUniqueId();

        // check if player is using the freeze wand
        EquipmentSlot hand = e.getHand();
        ItemMeta itemMeta = player.getInventory().getItem(hand).getItemMeta();

        if (itemMeta == null )   { return; }    // checks if item is air
        if (itemMeta.getLore() == null)   { return; }   // checks if item is vanilla
        if (!(itemMeta.getLore().contains("§5§oIce King's wand")))   { return; }   // checks if the item isn't the freeze wand

        // check if the player is frozen or not
        boolean frozen = playerProfileManager.getPlayerProfiles().get(rightClickedPlayerUUID).isFrozen();

        // freeze the player if player isn't frozen
        // unfreeze the player if player is frozen
        Player rightClickedPlayer = (Player) e.getRightClicked();
        if (frozen)   {
            playerProfileManager.getPlayerProfiles().get(rightClickedPlayerUUID).setFrozen(false);
            player.sendMessage(ChatColor.GOLD + "You have unfrozen " + ChatColor.RED + rightClickedPlayer.getName());
            rightClickedPlayer.sendMessage(ChatColor.RED + "You have been unfrozen by " + ChatColor.GOLD + player.getName());
        }else   {
            playerProfileManager.getPlayerProfiles().get(rightClickedPlayerUUID).setFrozen(true);
            player.sendMessage(ChatColor.GOLD + "You have frozen " + ChatColor.RED + rightClickedPlayer.getName());
            rightClickedPlayer.sendMessage(ChatColor.RED + "You have been frozen by " + ChatColor.GOLD + player.getName());
        }
    }

    /**
     * cancel player's movement if they are frozen
     * @param e PlayerMoveEvent
     */
    @EventHandler
    public void playerMove(PlayerMoveEvent e)   {

        Player player = e.getPlayer();
        boolean frozen = playerProfileManager.getPlayerProfiles().get(player.getUniqueId()).isFrozen();

        if (frozen)   {
            e.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You have been frozen!");
        }

    }

}
