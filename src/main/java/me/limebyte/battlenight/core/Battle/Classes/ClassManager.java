package me.limebyte.battlenight.core.Battle.Classes;

import java.util.ArrayList;
import java.util.Arrays;
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
	
    private static List<Class> classes = new ArrayList<Class>();
    private static final Config configFile = Config.CLASSES;
	
    public List<Class> getClasses() {
    	return classes;
    }
    
    public void loadClasses() {
    	ConfigurationManager.reload(configFile);
    	for (String className : ConfigurationManager.get(configFile).getConfigurationSection("Classes").getKeys(false)) {
    		String items = ConfigurationManager.get(configFile).getString("Classes." + className + ".Items", "");
    		String armour = ConfigurationManager.get(configFile).getString("Classes." + className + ".Armour", "");
    		classes.add(new Class(className, parseItems(items), parseItems(armour)));
		}
    }
    
    public void saveClasses() {
    	ConfigurationManager.reload(configFile);
    	for (Class c : classes) {
    		ConfigurationManager.get(configFile).set(c.getName() + ".Armour", parseItems(c.getArmour()));
    		ConfigurationManager.get(configFile).set(c.getName() + ".Items", parseItems(c.getItems()));
    	}
    	ConfigurationManager.save(configFile);
    }
    
    private List<ItemStack> parseItems(String rawItems) {
    	String[] splitRawItems = new String[1];
    	
    	if (rawItems.contains(",")) splitRawItems = rawItems.split(",");
    	else splitRawItems[0] = rawItems;
    	
    	List<ItemStack> items = new ArrayList<ItemStack>();
    	
		for (String item : splitRawItems) {
			item = item.trim();
			if (item == null) continue;
			
			int amount = 1;
			short data = 0;
			
			String[] part1 = item.split("x");
			String[] part2 = part1[0].split("e");
			String[] part3 = part2[0].split(":");
			
			int id;
			try {
				id = Integer.parseInt(part3[0]);
			} catch (NumberFormatException e1) {
				//TODO Log it
				continue;
			}
			
			// Do we have more than one item?
			if (part1.length == 2) {
				int a = 1;
				
				try {
					a = Integer.parseInt(part1[1]);
				} catch (NumberFormatException e) {}
				
				amount = a;
			}

			// Do we have any data?
			if (part3.length == 2) {
				short d = 0;
				
				try {
					d = Short.parseShort(part3[1]);
				} catch (NumberFormatException e) {}
				
				data = d;
			}
			
			ItemStack stack = new ItemStack(id, 1, data);
			
			if (amount > 1) {
				items.addAll(Arrays.asList(splitIntoStacks(stack, amount)));
			}
			else {
				items.add(stack);
			}
			
		}
		
    	return items;
    }
    
    private String parseItems(List<ItemStack> items) {
    	String rawItems = "";
    	
    	for (ItemStack item : items) {
    		int id = item.getTypeId();
    		int data = item.getDurability();
    		int amount = item.getAmount();
    		
    		rawItems += ", " + id + ":" + data + "x" + amount;
    	}
    	
    	return rawItems.substring(2);
    }
    
    // Thanks Njol for the improvements
    private ItemStack[] splitIntoStacks(ItemStack item, int amount) {
    	final int maxSize = item.getMaxStackSize();
    	final int fullStacks = (int) Math.floor(amount / maxSize);
    	 
    	ItemStack fullStack = item.clone();
    	ItemStack finalStack = item.clone();
    	fullStack.setAmount(maxSize);
    	finalStack.setAmount(amount % maxSize);
    	
    	ItemStack[] items = new ItemStack[fullStacks+1];
    	
    	for (int i=0; i<fullStacks; i++) {
    		items[i] = fullStack;
    	}
    	
    	items[items.length - 1] = finalStack;
    	
    	return items;
    }
    
}
