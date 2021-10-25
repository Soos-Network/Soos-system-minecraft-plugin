package de.CheckerPhil.soos.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ReportCommand implements CommandExecutor {

    String prefix = ChatColor.RED + ChatColor.BOLD.toString() + "| REPORT | " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (sender instanceof Player) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (args[1].equalsIgnoreCase("FLY") || args[1].equalsIgnoreCase("ANTIKNOCKBACK") || args[1].equalsIgnoreCase("AUTOCLICKER")|| args[1].equalsIgnoreCase("ROLEPLAY")){
                    if (target == null){
                        p.sendMessage(prefix + ChatColor.RED + "The player is offline");
                    }else{
                        if (target == p){
                            p.sendMessage(prefix + "Oh. You are the Imposter. Please" + ChatColor.RED + "DO NOT ABUSE" + ChatColor.RESET + "the report command.");
                        }else{

                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (all.hasPermission("Reports.see")){
                                        TextComponent c = new TextComponent(prefix + ChatColor.YELLOW + p.getName() + ChatColor.RESET + " reported " + ChatColor.YELLOW + target.getName() + ChatColor.RESET + " for " + ChatColor.YELLOW + args[1].toUpperCase() +ChatColor.RESET + ".");
                                        TextComponent clicker = new TextComponent(ChatColor.RED + "[ACCEPT]");
                                        clicker.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND , "/tp " + target.getName()));
                                        c.addExtra(clicker);

                                        all.spigot().sendMessage(c);
                                    }
                                }
                            p.sendMessage(prefix + ChatColor.GREEN +  "Thank you for your report! We will take care of it soon." + ChatColor.RED + "SPAMMING or ABUSE of the report command will be punished.");
                        }
                    }
                }else {
                    p.sendMessage(prefix + "Please choose one of these reasons:");
                    p.sendMessage("- FLY");
                    p.sendMessage("- ANTIKNOCKBACK");
                    p.sendMessage("- AUTOCLICKER");
                    p.sendMessage("- ROLEPLAY");
                }

            } else if (args.length == 1){
                p.sendMessage(prefix + "Please choose one of these reasons:");
                p.sendMessage("- FLY");
                p.sendMessage("- ANTIKNOCKBACK");
                p.sendMessage("- AUTOCLICKER");
                p.sendMessage("- ROLEPLAY");
            }/*else {
                p.sendMessage(prefix + "Bitte verwende: §a§1/report <Username> <Grund>");
            }*/
        }else {
           p.sendMessage(prefix + ChatColor.RED + "This command needs a player.");
        }
        return false;
    }
}
