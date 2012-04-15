package me.limebyte.battlenight.core.Configuration;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config.ConfigFile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class PlayerData {

    // Get Main Class
    public static BattleNight plugin;
    public PlayerData(BattleNight instance) {
        plugin = instance;
    }
	
    FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    
    public void reloadPlayer(Player p) {
    	if(config.getConfigurationSection(p.getName()) == null) {
    		config.set(p.getName() + ".stats.kills", obfuscate(0));
    		config.set(p.getName() + ".stats.deaths", obfuscate(0));
    	}
    }
    
    public void saveData(Player p) {
    	// Inventory
    	config.set(p.getName() + ".data.inv.main", p.getInventory().getContents());
    	config.set(p.getName() + ".data.inv.armor", p.getInventory().getArmorContents());
    	
    	// Health
    	config.set(p.getName() + ".data.health", p.getHealth());
    	
    	// Hunger
    	config.set(p.getName() + ".data.hunger.foodlevel", p.getFoodLevel());
    	config.set(p.getName() + ".data.hunger.saturation", p.getSaturation());
    	config.set(p.getName() + ".data.hunger.exhaustion", p.getExhaustion());
    	
    	// Experience
    	config.set(p.getName() + ".data.exp.level", p.getLevel());
    	config.set(p.getName() + ".data.exp.ammount", p.getExp());
    	
    	// GameMode
    	config.set(p.getName() + ".data.gamemode", p.getGameMode().getValue());
    	
    	// Flying
    	config.set(p.getName() + ".data.flight.allowed", p.getAllowFlight());
    	config.set(p.getName() + ".data.flight.flying", p.isFlying());
    	
    	// Locations
    	config.set(p.getName() + ".data.location", plugin.util.locationToString(p.getLocation()));
    	
    	// Sleep
    	config.set(p.getName() + ".data.sleepignored", p.isSleepingIgnored());
    	
    	// Information
    	config.set(p.getName() + ".data.info.displayname", p.getDisplayName());
    	config.set(p.getName() + ".data.info.listname", p.getPlayerListName());
    	
    	// Statistics
    	config.set(p.getName() + ".data.stats.tickslived", p.getTicksLived());
    	config.set(p.getName() + ".data.stats.nodamageticks", p.getNoDamageTicks());
    	
    	// State
    	config.set(p.getName() + ".data.state.remainingair", p.getRemainingAir());
    	config.set(p.getName() + ".data.state.falldistance", p.getFallDistance());
    	config.set(p.getName() + ".data.state.fireticks", p.getFireTicks());
    	
    	// Save it all
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    public void restoreData(Player p) {

    }

    protected void addKill(Player killer) {
    	config.set(killer.getName() + ".stats.kills", getKills(killer) + 1);
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    protected void addDeath(Player victom) {
    	config.set(victom.getName() + ".stats.deaths", getDeaths(victom) + 1);
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    public int getKills(Player p) {
        return deObfuscate(config.getString(".stats.kills"));
    }

    public int getDeaths(Player p) {
    	return deObfuscate(config.getString(".stats.deaths"));
    }

    public double getKDRatio(Player p) {
        return getKills(p) / getDeaths(p);
    }

    
    ////////////////////
    //      Util      //
    ////////////////////
    
    // Obfuscate kills/deaths
    private String obfuscate(int i) {
        return toBinaryString(i).replace("0", "A").replace("1", "B");
    }

    // De-Obfuscate kills/deaths
    private int deObfuscate(String i) {
        return parseInt(i.replace("A", "0").replace("B", "1"), 2);
    }
    
} 