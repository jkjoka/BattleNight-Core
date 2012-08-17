package me.limebyte.battlenight.core.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

	private String fileName;
	private String directory;
	private File file;
	private FileConfiguration fileConfig;
	private BattleNight plugin;
	
	public Configuration(BattleNight plugin, String fileName) {
        if (!plugin.isInitialized()) {
        	throw new IllegalArgumentException("Plugin must be initialized!");
        }
        
		this.fileName = fileName;
		this.plugin = plugin;
	}
	
	public Configuration(BattleNight plugin, String fileName, String directory) {
        if (!plugin.isInitialized()) {
        	throw new IllegalArgumentException("Plugin must be initialized!");
        }
		
		this.fileName = fileName;
		this.directory = directory;
		this.plugin = plugin;
	}
	
    public void init() {
        File dataFolder = plugin.getDataFolder();
        if (dataFolder == null) throw new IllegalStateException();
        
        if (directory == null) {
        	file = new File(plugin.getDataFolder(), fileName);
        } else {
        	file = new File(plugin.getDataFolder() + File.separator + directory, fileName);
        }
    	
        if(!file.exists()){
            try {
				file.getParentFile().mkdirs();
				Util.copyFile(plugin.getResource(fileName), file);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        fileConfig = new YamlConfiguration();
        reload();
    }
	
    public FileConfiguration get() {
        if (fileConfig == null) reload();
        return fileConfig;
    }
	
	public void reload() {
		try {
			fileConfig.load(file);
		} catch (FileNotFoundException e) {
			// TODO Log it
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Log it
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Log it
			e.printStackTrace();
		}
	}
	
    public void save() {
    	try {
			fileConfig.save(file);
		} catch (IOException e) {
			// TODO Log it
			e.printStackTrace();
		}
    }
    
}
