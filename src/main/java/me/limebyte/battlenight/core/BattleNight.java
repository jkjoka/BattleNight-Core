package me.limebyte.battlenight.core;

import java.util.logging.Logger;

import me.limebyte.battlenight.core.Battle.Battle;
import me.limebyte.battlenight.core.Battle.Classes.ClassManager;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;
import me.limebyte.battlenight.core.Configuration.PlayerData;

import org.bukkit.configuration.file.FileConfiguration;
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
    private ConfigurationManager configManager;
    private ClassManager classManager;
    private PlayerData playerData;
    public Tracks tracks;
    public Util util;
    private Battle battle;

    @Override
    public void onEnable() {

    	// Get plugin.yml and logger
    	pdFile = getDescription();
    	log = getLogger();
    	
    	// Register events
    	
    	// Configuration
    	configManager = new ConfigurationManager(this);
    	configManager.initialize();
    	
    	// Classes
    	classManager = new ClassManager(this);
    	
        // Link classes
        playerData = new PlayerData(this);
        tracks = new Tracks(this);
        util = new Util(this);
        battle = new Battle(this);
        
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
    
    
    @Override @Deprecated
    public FileConfiguration getConfig() {
        return null;
    }
    
    public ConfigurationManager getConfigManager() {
        return configManager;
    }
    
    public ClassManager getClassManager() {
        return classManager;
    }
    
    public PlayerData getPlayerData() {
    	return playerData;
    }
    
    public Battle getBattle() {
    	return battle;
    }

}
