package me.limebyte.battlenight.core.Battle.Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;
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
    
    public HashMap<String, Class> getClassNames() {
    	HashMap<String, Class> classList = new HashMap<String, Class>();
    	
    	for (Class c : classes) {
    		classList.put(c.getName(), c);
    	}
    	
    	return classList;
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
    		ConfigurationManager.get(configFile).set("Classes." + c.getName() + ".Armour", parseItems(c.getArmour()));
    		ConfigurationManager.get(configFile).set("Classes." + c.getName() + ".Items", parseItems(c.getItems()));
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
			Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
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
				} catch (NumberFormatException ex) {}
				
				amount = a;
			}

			// Do we have any enchantments?
			if (part2.length == 2) {
				String[] part4 = part2[1].split("/");
				
				for (String s : part4) {
					String[] splitEnchantment = s.replace("(", "").replace(")", "").split("~");
					int e = -1;
					int lvl = 1;
					Enchantment enc;
					
					// Checks
					if (splitEnchantment.length == 0) continue;
					
					// Get the enchantment id
					try {
						e = Integer.parseInt(splitEnchantment[0]);
					} catch (NumberFormatException ex) {
						continue;
					}
					
					enc = Enchantment.getById(e);
					
					// More Checks
					if (enc == null) continue;
					
					// Do we have a level?
					if (splitEnchantment.length == 2) {
						try {
							lvl = Integer.parseInt(splitEnchantment[1]);
						} catch (NumberFormatException ex) {}
					}
					else {
						lvl = enc.getStartLevel();
					}
					
					// Cap levels
					if (lvl > enc.getMaxLevel()) lvl = enc.getMaxLevel();
					
					// Add it
					enchantments.put(enc, lvl);
				}
			}
			
			// Do we have any data?
			if (part3.length == 2) {
				short d = 0;
				
				try {
					d = Short.parseShort(part3[1]);
				} catch (NumberFormatException ex) {}
				
				data = d;
			}
			
			ItemStack stack = new ItemStack(id, 1, data);
			
			if (!enchantments.isEmpty()) {
				try {
					stack.addEnchantments(enchantments);
				} catch (Exception ex) {
					//TODO Log it
				}
			}
			
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
    		
    		if (item == null) {
    			rawItems += ", none";
    		}
    		else {
	    		int id = item.getTypeId();
	    		int data = item.getDurability();
	    		int amount = item.getAmount();
	    		Map<Enchantment, Integer> enchantments =  item.getEnchantments();
	    		
	    		rawItems += ", " + id;
	    		if (data > 0)  rawItems += ":" + data;
	    		if (!item.getEnchantments().isEmpty()) {
	    			String rawEnchantments = "";
	    			rawItems += "e(";
	    			
	    			for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
	    			    rawEnchantments += "/" + enchantment.getKey().getId() + "~" + enchantment.getValue();
	    			}
	    			rawItems += rawEnchantments.substring(1);
	    			
	    			rawItems += ")";
	    		}
	    		if (amount > 1) rawItems += "x" + amount;
    		}
    	}
    	
    	return rawItems.substring(2);
    }
    
    // Thanks Njol for the improvements
    private ItemStack[] splitIntoStacks(ItemStack item, int amount) {
    	final int maxSize = item.getMaxStackSize();
    	final int fullStacks = (int) Math.floor(amount / maxSize);
    	final int finalStackAmount = amount % maxSize;
    	 
    	ItemStack fullStack = item.clone();
    	ItemStack finalStack = item.clone();
    	fullStack.setAmount(maxSize);
    	finalStack.setAmount(finalStackAmount);
    	
    	ItemStack[] items;
    	
    	if (finalStackAmount > 0) items = new ItemStack[fullStacks+1];
    	else items = new ItemStack[fullStacks];
    	
    	for (int i=0; i<fullStacks; i++) {
    		items[i] = fullStack;
    	}
    	
    	if (finalStackAmount > 0) items[items.length - 1] = finalStack;
    	
    	return items;
    }
    
}
