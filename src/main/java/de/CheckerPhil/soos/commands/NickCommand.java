package de.CheckerPhil.soos.commands;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    String prefix = ChatColor.GOLD + ChatColor.BOLD.toString() +  "| NICK | " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("Nick.nick")){
            if (args.length == 1){
                Player player = Bukkit.getPlayer(sender.getName());
                player.setDisplayName(args[0]);
                player.sendMessage(prefix + ChatColor.GREEN + "You should be now nicked as " + ChatColor.YELLOW + args[0] + ChatColor.GREEN + ".");
            }else {
                sender.sendMessage(prefix + ChatColor.RED + "Please use /nick <nickname>");
            }
        }else {
            sender.sendMessage(ChatColor.RED + "You dont like your Minecraft Name. Change it on Minecraft.net");
        }

        return false;
    }
}
