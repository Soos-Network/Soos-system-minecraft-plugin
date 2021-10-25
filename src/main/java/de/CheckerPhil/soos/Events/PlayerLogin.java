package de.CheckerPhil.soos.Events;

import de.CheckerPhil.soos.SoosSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {

    private final SoosSystem plugin;

    public PlayerLogin(SoosSystem plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        Player player = e.getPlayer();

        if (player.hasPermission("System.CanJoinFullServer")){
            if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()){
                for (Player players : Bukkit.getOnlinePlayers()){
                    if (!players.hasPermission("System.CanStayAtFullServer")){
                        players.kickPlayer("You got kicked to let a player with a higher rank on the Server."
                                + "\n"
                                + "Buy Premium ingame with your Sooscoins to avoid this.");
                        e.allow();
                        return;
                    }
                }
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "It seems like the Server is full of Premium members. Try again later!");
            }
        }else {
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, "The Server is full!"
                    + "\n"
                    + "Buy Premium ingame with your Sooscoins to join a full Server.");
        }
    }
}
