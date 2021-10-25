package de.CheckerPhil.soos.commands;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class BanInfoCommand implements CommandExecutor {

    private final SoosSystem plugin;
    String prefix = ChatColor.RED + ChatColor.BOLD.toString() + "| BAN | " + ChatColor.RESET;

    public BanInfoCommand(SoosSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("baninfo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("Bans.seeBans")) {
            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                for (String section : plugin.getConfig().getConfigurationSection("bannend_players").getKeys(false)) {
                    if (section.equals(target.getUniqueId().toString())) {
                        sender.sendMessage("----" + prefix + "----");
                        sender.sendMessage(prefix + ChatColor.GRAY + "Infos about (last) Ban of " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY +":"
                                + "\n"
                                + "Reason: " + ChatColor.YELLOW + plugin.getConfig().getString("banned_players." + target.getUniqueId().toString() + ".reason") + ChatColor.GRAY
                                + "\n"
                                + "Banned fron: " + ChatColor.YELLOW + plugin.getConfig().getString("bannend_players." + target.getUniqueId().toString() + ".banner") + ChatColor.GRAY
                                + "\n"
                                + "Date & Time: " + ChatColor.YELLOW + plugin.getConfig().getString("banned_players." + target.getUniqueId().toString() + ".time" + ChatColor.GRAY)
                                + "\n"
                                + "Active ban: " + ChatColor.YELLOW + plugin.getConfig().getString("banned_players." + target.getUniqueId().toString() + ".banned"));
                        sender.sendMessage("----" + prefix + "----");
                    }else {
                        sender.sendMessage(ChatColor.RED + "There is no data of this user in our database!");
                    }
                }
            }
        }else {
            sender.sendMessage(prefix + ChatColor.RED + "Apply for Supporter or Moderator to execute this command.");
        }

return false;
    }}
