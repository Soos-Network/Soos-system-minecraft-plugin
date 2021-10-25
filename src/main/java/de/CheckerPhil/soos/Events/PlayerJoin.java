package de.CheckerPhil.soos.Events;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final SoosSystem plugin;

    public PlayerJoin(SoosSystem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player player = e.getPlayer();

        if (plugin.getConfig().getConfigurationSection("bannend_players") != null){
        for (String section: plugin.getConfig().getConfigurationSection("bannend_players").getKeys(false)){
            if (section.equals(player.getUniqueId().toString())){
                if(plugin.getConfig().getString("bannend_players." + player.getUniqueId().toString() + ".banned") == "true"){
                    player.kickPlayer(ChatColor.RED + "You got banned."
                            + "\n"
                            + "Reason: " +ChatColor.YELLOW + plugin.getConfig().getString("banned_players."+player.getUniqueId().toString()+".reason")
                            +"\n"
                            + ChatColor.GRAY +"Write a ban appeal on " + ChatColor.DARK_AQUA + "https://discord.gg/n3DGWdfgDX" + ChatColor.GRAY +".");
                }

            }}
        }
    }
}
