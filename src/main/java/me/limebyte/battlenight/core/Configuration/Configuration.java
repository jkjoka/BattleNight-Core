package me.limebyte.battlenight.core.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import me.limebyte.battlenight.core.BattleNight;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

	private String name;
	private String directory;
	private File file = null;
	private FileConfiguration fileConfig = null;
	private String header;
	
	public static BattleNight plugin;
	
	public Configuration(BattleNight plugin, String name, String header) {
		this.name = name;
		this.header = header;
		this.directory = null;
		Configuration.plugin = plugin;
	}
	
	public Configuration(BattleNight plugin, String name, String header, String directory) {
		this.name = name;
		this.header = header;
		this.directory = File.separator + directory;
		Configuration.plugin = plugin;
	}
	
    public void init() {
    	
    	this.reload();
    	this.fileConfig.options().header(this.getHeader());
    	this.fileConfig.options().copyHeader(true);
    	this.fileConfig.options().copyDefaults(true);
    	this.save();
    }
	
    public FileConfiguration get() {
        if (this.fileConfig == null) {
            this.reload();
        }
        return this.fileConfig;
    }
	
	public void reload() {
	    if (this.file == null) {
			if (this.directory == null) this.file = new File(plugin.getDataFolder(), this.name);
			else this.file = new File(plugin.getDataFolder() + this.directory, this.name);
	    }
	    this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
     
        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(this.name);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.fileConfig.setDefaults(defConfig);
        }
	}
	
    public void save() {
        try {
            this.get().save(this.file);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
	
    private String getHeader() {
    	String nl = "\r\n";
    	String configHeader = "BattleNight Version " + plugin.getDescription().getVersion() + nl + this.name.substring(0, this.name.length()-4);
		if (this.directory == null) configHeader += " Configuration File";
		else 				   configHeader += " Data File";
		
		return configHeader + nl + this.header + nl;
    }
    
}
