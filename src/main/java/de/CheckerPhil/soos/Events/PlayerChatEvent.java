package de.CheckerPhil.soos.Events;


import de.CheckerPhil.soos.SoosSystem;
import de.CheckerPhil.soos.commands.MuteCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    private final SoosSystem plugin;
    public static String prefix = ChatColor.RED + ChatColor.BOLD.toString() + " | TEAMCHAT | " + ChatColor.RESET;

    public PlayerChatEvent(SoosSystem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if (SoosSystem.mute.contains(p.getName())){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "| MUTE | You got Muted!");
        }

        if (e.getMessage().contains("Penis") ||
                e.getMessage().contains("Penner") ||
                e.getMessage().contains("Arschloch")||
                e.getMessage().contains("Arsch")||
                e.getMessage().contains("bg")||
                e.getMessage().contains("Fick")||
                e.getMessage().contains("Ficken")||
                e.getMessage().contains("Hurensohn") ||
                e.getMessage().contains("Hure") ||
                e.getMessage().contains("Sex") ||
                e.getMessage().contains("Bastard")||
                e.getMessage().contains("Idiot") ||
                e.getMessage().contains("Muschi")){
            e.setCancelled(true);
            SoosSystem.mute.add(e.getPlayer().getName());
            e.getPlayer().sendMessage("| MUTE | Thats not a nice word!");
        }

        if (e.getMessage().startsWith("@team")){
            if (p.hasPermission("team.send")){
                String msg = e.getMessage();
                for (Player team : Bukkit.getOnlinePlayers()){
                    if (team.hasPermission("team.see")){
                        e.setCancelled(true);
                        team.sendMessage(prefix + p.getName() + ChatColor.RED + " >>" + ChatColor.RESET + msg.replace("@team", ChatColor.GRAY.toString()));
                    }
                }
            }else {
                e.setCancelled(true);
            }
        }
    }
}
