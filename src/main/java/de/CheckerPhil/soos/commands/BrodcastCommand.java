package de.CheckerPhil.soos.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BrodcastCommand implements CommandExecutor {

    String prefix = ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + " |BRODCAST | " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("Brodcast.send")){
            if (args.length >= 1){
                String msg = "";

                for (int i = 0; i < args.length; i++){
                    msg = msg + args[i] + " ";
                }
                Bukkit.broadcastMessage("----" + prefix + "----");
                Bukkit.broadcastMessage(msg);
                Bukkit.broadcastMessage("----" + prefix + "----");

            }else {
                sender.sendMessage(prefix + ChatColor.RED + "Please use /brodcast <message>");
            }

        }else {
            sender.sendMessage(prefix + ChatColor.RED + "Maybe your need a bigger megaphone or the rank Moderator or Supporter.");
        }
        return false;
    }
}
