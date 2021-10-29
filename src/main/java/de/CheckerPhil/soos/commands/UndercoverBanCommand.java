package de.CheckerPhil.soos.commands;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UndercoverBanCommand implements CommandExecutor {

    private final SoosSystem plugin;
    String prefix = ChatColor.RED + ChatColor.BOLD.toString() + "| BAN | " + ChatColor.RESET;

    public UndercoverBanCommand(SoosSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("undercoverban").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        if (sender.hasPermission("Bans.ban")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);

                    /*if (target == null){
                        OfflinePlayer offlinetarget = Bukkit.getOfflinePlayer(args[0]);

                        plugin.getConfig().set("bannend_players." + offlinetarget.getUniqueId().toString() + ".banned", "true");
                        plugin.getConfig().set("bannend_players." + offlinetarget.getUniqueId().toString() + ".banner", sender.getName());
                        plugin.getConfig().set("banned_players." + offlinetarget.getUniqueId().toString() + ".reason", args[1].toUpperCase());
                        plugin.getConfig().set("banned_players." + offlinetarget.getUniqueId().toString() + ".time", dtf.format(now));
                        plugin.saveConfig();

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("Bans.seeBans")) {
                                all.sendMessage(prefix + sender.getName() + "hat" + target.getName() + "für" + args[1] + "gebannt.");
                            }
                        }

                        }


                    }else {*/
                if (target == sender) {
                    sender.sendMessage(prefix + "You can not ban yourself.");
                } else {
                    if (args[1].equalsIgnoreCase("HACKING") || args[1].equalsIgnoreCase("BUGUSING") || args[1].equalsIgnoreCase("SECURITY") || args[1].equalsIgnoreCase("ROLEPLAY") || args[1].equalsIgnoreCase("BANUMGEHUNG")) {
                        //if (args[0].equals(target)) {
                        plugin.getConfig().set("bannend_players." + target.getUniqueId().toString() + ".banned", "true");
                        plugin.getConfig().set("bannend_players." + target.getUniqueId().toString() + ".banner", sender.getName());
                        plugin.getConfig().set("banned_players." + target.getUniqueId().toString() + ".reason", args[1].toUpperCase());
                        plugin.getConfig().set("banned_players." + target.getUniqueId().toString() + ".time", dtf.format(now));
                        plugin.saveConfig();

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("Bans.seeBans")) {
                                all.sendMessage(prefix + sender.getName() + " banned " + target.getName() + " undercover for" + args[1] + ".");
                            }
                        }
                    }else{
                        sender.sendMessage(prefix + ChatColor.GREEN +  "Banned Player successfully.");
                        sender.sendMessage(prefix + ChatColor.RED +  "Please use /ban " + target.getName() + " [Reason] to specify a reason.");
                        sender.sendMessage(ChatColor.RED + "- HACKING");
                        sender.sendMessage(ChatColor.RED + "- BUGUSING");
                        sender.sendMessage(ChatColor.RED + "- SECURITY");
                        sender.sendMessage(ChatColor.RED + "- ROLEPLAY");
                        sender.sendMessage(ChatColor.RED + "- BANUMGEHUNG");

                        plugin.getConfig().set("bannend_players." + target.getUniqueId().toString() + ".banned", "true");
                        plugin.getConfig().set("bannend_players." + target.getUniqueId().toString() + ".banner", sender.getName());
                        plugin.getConfig().set("banned_players." + target.getUniqueId().toString() + ".reason", "VIOLATION OF OUR TERMS OF SERVICES");
                        plugin.getConfig().set("banned_players." + target.getUniqueId().toString() + ".time", dtf.format(now));
                        plugin.saveConfig();

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("Bans.seeBans")) {
                                all.sendMessage(prefix + sender.getName() + " banned " + target.getName() + " undercover for" + args[1] + ".");
                            }
                        }
                    }
                }
            }else {}
        } else {
            sender.sendMessage(prefix + ChatColor.RED + "Apply for Supporter or Moderator to execute this command.");
        }

        return false;
    }
}