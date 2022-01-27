package main;

import commands.commands;
import core.logger;
import eventlistener.eventlistener;
import handler.timer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static main.whes1015.VersionCode;

public class main extends JavaPlugin implements Listener {

    public static Integer Support=22031;
    public static Integer stop=0;

    @Override
    public void onEnable() {
            if (VersionCode >= Support) {
                Objects.requireNonNull(getCommand("ec")).setExecutor(new commands(this));
                getServer().getPluginManager().registerEvents(new eventlistener(), this);
                logger.log("INFO", "Economy_onEnable", "Loading Success! - Designed by ExpTech.tw!");
                timer.main();
            } else {
                logger.log("WARN", "Economy_onEnable", "Please update your Core version");
                Bukkit.getPluginManager().disablePlugins();
            }
    }

    @Override
    public void onDisable(){
        timer.timer.cancel();
        logger.log("INFO", "Economy_timer", "Timer Cancel");
        logger.log("INFO","Economy_onDisable","Closing! Version: "+getDescription().getVersion());
    }
}
