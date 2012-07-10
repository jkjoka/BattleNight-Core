package me.limebyte.battlenight.core.Battle.Classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;

public class ClassManager {

    // Get Main Class
    public static BattleNight plugin;
    public ClassManager(BattleNight instance) {
        plugin = instance;
    }
	
    private List<Class> classes;
    private static final ConfigurationManager cm = plugin.getConfigManager();
    private static final Config configFile = Config.CLASSES;
	
    public void loadClasses() {
    	classes = new ArrayList<Class>();
    }
    
    public void saveClasses() {
    	for (Class c : classes) {
    		cm.get(configFile).set(c.getName() + ".Armour", parseItems(c.getArmour()));
    		cm.get(configFile).set(c.getName() + ".Items", parseItems(c.getItems()));
    	}
    }
    
    private List<ItemStack> parseItems(String rawitems) {
    	List<ItemStack> items = new ArrayList<ItemStack>(); 
    	return items;
    }
    
    private String parseItems(List<ItemStack> items) {
    	return "";
    }
    
}
