package de.CheckerPhil.soos.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    String prefix = ChatColor.RED + ChatColor.BOLD.toString() +  "| BAN |";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("Bans.ban")) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (args[1].equalsIgnoreCase("HACKING") || args[1].equalsIgnoreCase("BUGUSING") || args[1].equalsIgnoreCase("SECURITY")|| args[1].equalsIgnoreCase("ROLEPLAY")|| args[1].equalsIgnoreCase("BANUMGEHUNG")){
                    if (target == null){
                        sender.sendMessage(prefix + "The target is offline");
                    }else{
                        if (target == sender){
                            sender.sendMessage(prefix + "You can not kick yourself.");
                        }else{

                            target.kickPlayer(ChatColor.RED + "You got kicked."
                                    + "\n"
                                    + "Reason: " + ChatColor.YELLOW + args[1].toUpperCase());
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (all.hasPermission("Bans.seeBans")){
                                        all.sendMessage(prefix + sender.getName() + " kicked " + target.getName() + "[" + args[1].toUpperCase() +"]");
                                    }
                                }

                        }
                    }
                }else {

                    sender.sendMessage(prefix + "Please choose one of these reasons:");
                    sender.sendMessage("- HACKING");
                    sender.sendMessage("- BUGUSING");
                    sender.sendMessage("- SECURITY");
                    sender.sendMessage("- ROLEPLAY");
                    sender.sendMessage("- BANUMGEHUNG");
                }

            } else if (args.length == 1){

                sender.sendMessage(prefix + "Please choose one of these reasons:");
                sender.sendMessage("- HACKING");
                sender.sendMessage("- BUGUSING");
                sender.sendMessage("- SECURITY");
                sender.sendMessage("- ROLEPLAY");
                sender.sendMessage("- BANUMGEHUNG");

            }/*else {
                p.sendMessage(prefix + "Bitte verwende: §a§1/kick <Username> <Grund>");
            }*/
        }else {
            sender.sendMessage(prefix + ChatColor.RED + "Apply for Supporter or Moderator to execute this command.");
        }
        return false;
    }
}
