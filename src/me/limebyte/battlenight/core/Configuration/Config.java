package me.limebyte.battlenight.core.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.limebyte.battlenight.core.BattleNight;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the configuration manager.
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Config {

    // Files
    File mainFile;
    File classesFile;
    File tracksFile;
    File waypointsFile;
    File playersFile;

    // FileConfigurations
    FileConfiguration main;
    FileConfiguration classes;
    FileConfiguration tracks;
    FileConfiguration waypoints;
    FileConfiguration players;
    
    // Get Main Class
    public static BattleNight plugin;
    public Config(BattleNight instance) {
        plugin = instance;
    }

    public void enable() {
        // Initialize Files
        mainFile      = new File(plugin.getDataFolder().getPath(), "Config.yml");
        classesFile   = new File(plugin.getDataFolder().getPath(), "Classes.yml");
        tracksFile    = new File(plugin.getDataFolder().getPath(), "Tracks.yml");
        waypointsFile = new File(plugin.getDataFolder() + "/PluginData", "Waypoints.dat");
        playersFile   = new File(plugin.getDataFolder() + "/PluginData", "Players.dat");
        
        // Use firstRun(); method
        firstRun();
        
        // Initialize FileConfigurations
        main = new YamlConfiguration();
        classes = new YamlConfiguration();
        tracks = new YamlConfiguration();
        waypoints = new YamlConfiguration();
        players = new YamlConfiguration();
        
        // Load Files
        for (ConfigFile file : ConfigFile.values()) {
            reload(file);
        }
        
    }
    
    public enum ConfigFile {
        MAIN, CLASSES, TRACKS, WAYPOINTS, PLAYERS;
    }

    private FileConfiguration getFileConfig(ConfigFile cf) {
        switch (cf) {
            case MAIN:
                return main;
            case CLASSES:
                return classes;
            case TRACKS:
                return tracks;
            case WAYPOINTS:
                return waypoints;
            case PLAYERS:
                return players;
            default:
                return null;
        }
    }

    private File getFile(ConfigFile cf) {
        switch (cf) {
            case MAIN:
                return mainFile;
            case CLASSES:
                return classesFile;
            case TRACKS:
                return tracksFile;
            case WAYPOINTS:
                return waypointsFile;
            case PLAYERS:
                return playersFile;
            default:
                return null;
        }
    }

    private void firstRun() {
		if (!mainFile.exists()) {
			mainFile.getParentFile().mkdirs();
			copy(plugin.getResource("Config.yml"), mainFile);
		}
		if (!classesFile.exists()) {
			classesFile.getParentFile().mkdirs();
			copy(plugin.getResource("Classes.yml"), classesFile);
		}
		if (!tracksFile.exists()) {
			tracksFile.getParentFile().mkdirs();
			copy(plugin.getResource("Tracks.yml"), tracksFile);
		}
		if (!waypointsFile.exists()) {
			waypointsFile.getParentFile().mkdirs();
			copy(plugin.getResource("Waypoints.dat"), waypointsFile);
		}
		if (!playersFile.exists()) {
			playersFile.getParentFile().mkdirs();
			copy(plugin.getResource("Players.dat"), playersFile);
		}
    }
    
    private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void reload(ConfigFile cf) {
    	try {
    		getFileConfig(cf).load(getFile(cf));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public FileConfiguration get(ConfigFile cf) {
        return (getFileConfig(cf));
    }

    public void save(ConfigFile file) {
    	try {
    		getFileConfig(file).save(getFile(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
