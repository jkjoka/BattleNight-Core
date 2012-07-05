package me.limebyte.battlenight.core.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import me.limebyte.battlenight.core.BattleNight;

public class ConfigurationManager {
	
	private Configuration main;
	private Configuration classes;
	private Configuration tracks;
	private Configuration waypoints;
	private Configuration players;
    
    // Get Main Class
    public static BattleNight plugin;
    public ConfigurationManager(BattleNight instance) {
        plugin = instance;
    }
	
    public void initialize() {
    	main = new Configuration(plugin, "Config.yml");
    	classes = new Configuration(plugin, "Classes.yml");
    	tracks = new Configuration(plugin, "Tracks.yml");
    	waypoints = new Configuration(plugin, "Waypoints.dat", "PluginData");
    	players = new Configuration(plugin, "Players.dat", "PluginData");
    	
    	main.initialize();
    	classes.initialize();
    	tracks.initialize();
    	waypoints.initialize();
    	players.initialize();
    }
    
    public void save(Config config) {
        switch (config) {
            case MAIN:
                main.save();
                break;
            case CLASSES:
                classes.save();
                break;
            case TRACKS:
                tracks.save();
                break;
            case WAYPOINTS:
                waypoints.save();
                break;
            case PLAYERS:
                players.save();
                break;
            default:
                saveAll();
                break;
        }
    }
    
    public void saveAll() {
    	main.save();
    	classes.save();
    	tracks.save();
    	waypoints.save();
    	players.save();
    }
    
    public void reload(Config config) {
        switch (config) {
            case MAIN:
                main.reload();
                break;
            case CLASSES:
                classes.reload();
                break;
            case TRACKS:
                tracks.reload();
                break;
            case WAYPOINTS:
                waypoints.reload();
                break;
            case PLAYERS:
                players.reload();
                break;
            default:
                reloadAll();
                break;
        }
    }
    
    public void reloadAll() {
    	main.reload();
    	classes.reload();
    	tracks.reload();
    }
    
    public FileConfiguration get(Config config) {
        switch (config) {
            case MAIN:
                return main.get();
            case CLASSES:
                return classes.get();
            case TRACKS:
                return tracks.get();
            case WAYPOINTS:
                return waypoints.get();
            case PLAYERS:
                return players.get();
            default:
                return null;
        }
    }
	
}
