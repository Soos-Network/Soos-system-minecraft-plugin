package de.CheckerPhil.soos.commands;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    String prefix = ChatColor.RED + ChatColor.BOLD.toString() +  "| MUTE | " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("Bans.mute")){
            if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null){
                    if (SoosSystem.mute.contains(target.getName())){
                        SoosSystem.mute.remove(target.getName());
                        sender.sendMessage(prefix + ChatColor.GREEN + "The player " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " got unmuted.");
                    }else {
                        SoosSystem.mute.add(target.getName());
                        sender.sendMessage(prefix + ChatColor.GREEN + "The player " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " got muted.");
                    }
                }else {
                    sender.sendMessage(prefix + ChatColor.RED + "The target is offline.");
                }
            }else {
                sender.sendMessage(prefix + "Please use /mute <playername>");
            }
        }else {
            sender.sendMessage(ChatColor.RED + "Only Supporters and Moderators have the ability to make players spechless (because there are so amazing).");
        }

        return false;
    }
}
