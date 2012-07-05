package me.limebyte.battlenight.core.Configuration;

import java.io.File;
import java.io.IOException;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

	private String name;
	private String directory;
	private File file;
	private FileConfiguration fileConfig;
	public static BattleNight plugin;
	
	public Configuration(BattleNight plugin, String name) {
		this.name = name;
		this.directory = null;
		Configuration.plugin = plugin;
	}
	
	public Configuration(BattleNight plugin, String name, String directory) {
		this.name = name;
		this.directory = File.separator + directory;
		Configuration.plugin = plugin;
	}
	
	public void initialize() {
		if (directory == null) file = new File(plugin.getDataFolder(), name);
		else file = new File(plugin.getDataFolder() + directory, name);
			
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			Util.copyFile(plugin.getResource(name), file);
		}
		
		fileConfig = new YamlConfiguration();
		reload();
	}
	
    public FileConfiguration get() {
        return fileConfig;
    }
	
	public void reload() {
    	try {
    		fileConfig.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void save() {
    	try {
    		fileConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
