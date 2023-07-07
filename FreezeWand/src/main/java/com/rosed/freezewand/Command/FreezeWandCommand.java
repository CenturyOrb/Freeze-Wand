package com.rosed.freezewand.Command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FreezeWandCommand implements CommandExecutor {

    /**
     * give player freeze wand
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // check if the command user is a player
        if (!(sender instanceof Player))   { return false; }
        Player player = (Player) sender;

        // check for perms
        if (player.hasPermission("freezewand.freeze"))   {
            player.sendMessage(ChatColor.GREEN + "You have been given the " + ChatColor.GOLD + "Freeze Wand");

            // give the player freeze wand
            ItemStack freezeWandStack = new ItemStack(Material.STICK);
            ItemMeta freezeWandMeta = freezeWandStack.getItemMeta();
            freezeWandMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Freeze Wand"));
            freezeWandMeta.setLocalizedName("freezewand.freezewand");
            List<String> freezeWandLore = new ArrayList<>();
            freezeWandLore.add(ChatColor.translateAlternateColorCodes('&', "&5&oIce King's wand"));
            freezeWandMeta.setLore(freezeWandLore);
            freezeWandStack.setItemMeta(freezeWandMeta);

            player.getInventory().addItem(freezeWandStack);

        }else   {
            player.sendMessage(ChatColor.RED + "You don't have permission to use " + ChatColor.GOLD + "/speed");
        }


        return false;
    }
}
