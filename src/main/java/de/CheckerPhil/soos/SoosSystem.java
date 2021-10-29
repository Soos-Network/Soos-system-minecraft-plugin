package de.CheckerPhil.soos;

import de.CheckerPhil.soos.Events.PlayerChatEvent;
import de.CheckerPhil.soos.Events.PlayerJoin;
import de.CheckerPhil.soos.Events.PlayerLogin;
import de.CheckerPhil.soos.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SoosSystem extends JavaPlugin {

    public static ArrayList<String> mute = new ArrayList<String>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("SoosSystem ist nun Aktiv!");
        getCommand("ping").setExecutor(new PongCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("brodcast").setExecutor(new BrodcastCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("unnick").setExecutor(new UnnickCommand());
        getCommand("roundkick").setExecutor(new RoundKickCommand());
        new BanCommand(this);
        new UndercoverBanCommand(this);
        new PlayerJoin(this);
        new BanInfoCommand(this);
        new UnbanCommand(this);
        new PlayerChatEvent(this);
        new PlayerLogin(this);
        getConfig().options().copyDefaults();
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("SoosSystem ist nun nicht mehr Aktiv!");
    }
}
