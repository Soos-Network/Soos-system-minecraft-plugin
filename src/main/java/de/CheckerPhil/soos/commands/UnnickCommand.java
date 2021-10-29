package de.CheckerPhil.soos.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnnickCommand implements CommandExecutor{

        String prefix = ChatColor.GOLD + ChatColor.BOLD.toString() + "| NICK | " + ChatColor.RESET;

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender.hasPermission("Nick.nick")){
                if (args.length == 1){
                    Player player = Bukkit.getPlayer(sender.getName());
                    player.setDisplayName(player.getName());
                    player.sendMessage(prefix + ChatColor.GREEN + "You should now have your original username back.");
                }else {
                    sender.sendMessage(prefix + ChatColor.RED + "Please use /nick <nickname>");
                }
            }else {
                sender.sendMessage(prefix + ChatColor.RED + "Your username should be the same as before, because you have no permissions to execute this command.");
            }

            return false;
        }
}
