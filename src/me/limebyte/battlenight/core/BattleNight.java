package me.limebyte.battlenight.core;

import java.util.logging.Logger;

import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.PlayerData;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */

public class BattleNight extends JavaPlugin {

    public PluginDescriptionFile pdFile;
    public Logger log;
    public Config config = new Config(this);
    private PlayerData playerData = new PlayerData(this);
    public Tracks tracks = new Tracks(this);
    public Util util = new Util(this);

    @Override
    public void onEnable() {

    	// Get plugin.yml and logger
    	pdFile = getDescription();
    	log = getLogger();
    	
    	// Register events
    	
        // Reload configuration files
        config.reload(Config.ConfigFile.MAIN);
        config.reload(Config.ConfigFile.CLASSES);
        config.reload(Config.ConfigFile.TRACKS);
        config.reload(Config.ConfigFile.WAYPOINTS);
        config.reload(Config.ConfigFile.PLAYERS);

        // Load command class
        Commands cmdExecutor = new Commands(this);
        getCommand("bn").setExecutor(cmdExecutor);

        // Print enable message to the console
        log.info("Version " + pdFile.getVersion() + " enabled!");
        log.info("Made by LimeByte.");
    }

    @Override
    public void onDisable() {
        // Print disable message to the console
        log.info("Version " + pdFile.getVersion() + " disabled.");
    }
    
    public PlayerData getPlayerData() {
    	return playerData;
    }

}
