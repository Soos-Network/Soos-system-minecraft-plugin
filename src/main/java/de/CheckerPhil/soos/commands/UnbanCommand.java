package de.CheckerPhil.soos.commands;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {

    private final SoosSystem plugin;
    String prefix = ChatColor.RED + ChatColor.BOLD.toString() + "| MUTE | " + ChatColor.RESET;

    public UnbanCommand(SoosSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("unban").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("Bans.Unban")){
            if (args.length == 1){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                plugin.getConfig().set("bannend_players." + target.getUniqueId().toString() + ".banned", "false");
                plugin.saveConfig();

                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("Bans.seeBans")) {
                        all.sendMessage(prefix +ChatColor.YELLOW + sender.getName() + ChatColor.GREEN + "removed the ban of " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN);
                    }
                }
            }else{
                sender.sendMessage("Please use /unban <playername>");
            }
        }else {
            sender.sendMessage(prefix + ChatColor.RED +"You can also use a ban appeal. That may work...");
        }

        return false;
    }
}
