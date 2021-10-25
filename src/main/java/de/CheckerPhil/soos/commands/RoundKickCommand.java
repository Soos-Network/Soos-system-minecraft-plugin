package de.CheckerPhil.soos.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoundKickCommand implements CommandExecutor {

    String prefix = ChatColor.RED + ChatColor.BOLD.toString() + "| ROUNDKICK | " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("Bans.roundKick")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                    if (target == null){
                        sender.sendMessage(prefix + "The target is offline");
                    }else{
                        if (target == sender){
                            sender.sendMessage(prefix + "You can not kick yourself.");
                        }else{
                            if (target.getWorld() == Bukkit.getWorld("Soos")){
                                target.teleport(Bukkit.getWorld("Soos").getSpawnLocation());
                                target.sendMessage(prefix + ChatColor.YELLOW + "A Friend or Youtuber has kicked you out of the Round.");
                                sender.sendMessage(prefix + ChatColor.GREEN + "Succesfully kicked" + target.getName() + "out of the Round.");
                            }else {
                                sender.sendMessage(prefix + ChatColor.RED + "The player is not in a minigames world.");
                            }
                        }
                    }
        }else {
            sender.sendMessage(prefix + ChatColor.RED + "Only Youtuber and Friends can perform this command.");
        }
    }
        return false;
}}
