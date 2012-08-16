package me.limebyte.battlenight.core.managers;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

import java.util.Arrays;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class PlayerManager {

    // Get Main Class
    public static BattleNight plugin;
    public PlayerManager(BattleNight instance) {
        plugin = instance;
    }
    
    private static void reloadStats(Player p) {
    	FileConfiguration config = ConfigurationManager.get(Config.PLAYERS);
    	
    	if(config.getConfigurationSection(p.getName()) == null) {
    		config.set(p.getName() + ".stats.kills", obfuscate(0));
    		config.set(p.getName() + ".stats.deaths", obfuscate(0));
    	}
    }
    
    public static void save(Player p) {
    	FileConfiguration config = ConfigurationManager.get(Config.PLAYERS);
    	
    	// Reload the config
    	ConfigurationManager.reload(Config.PLAYERS);
    	
    	// Statistics
    	reloadStats(p);
    	
    	// Inventory
    	config.set(p.getName() + ".data.inv.main", Arrays.asList(p.getInventory().getContents()));
    	config.set(p.getName() + ".data.inv.armor", Arrays.asList(p.getInventory().getArmorContents()));
    	
    	// Health
    	config.set(p.getName() + ".data.health", p.getHealth());
    	
    	// Hunger
    	config.set(p.getName() + ".data.hunger.foodlevel", p.getFoodLevel());
    	config.set(p.getName() + ".data.hunger.saturation", Float.toString(p.getSaturation()));
    	config.set(p.getName() + ".data.hunger.exhaustion", Float.toString(p.getExhaustion()));
    	
    	// Experience
    	config.set(p.getName() + ".data.exp.level", p.getLevel());
    	config.set(p.getName() + ".data.exp.ammount", Float.toString(p.getExp()));
    	
    	// GameMode
    	config.set(p.getName() + ".data.gamemode", p.getGameMode().getValue());
    	
    	// Flying
    	config.set(p.getName() + ".data.flight.allowed", p.getAllowFlight());
    	config.set(p.getName() + ".data.flight.flying", p.isFlying());
    	
    	// Locations
    	config.set(p.getName() + ".data.location", Util.locationToString(p.getLocation()));
    	
    	// Sleep
    	config.set(p.getName() + ".data.sleepignored", p.isSleepingIgnored());
    	
    	// Information
    	config.set(p.getName() + ".data.info.displayname", p.getDisplayName());
    	config.set(p.getName() + ".data.info.listname", p.getPlayerListName());
    	
    	// State
    	config.set(p.getName() + ".data.stats.tickslived", p.getTicksLived());
    	config.set(p.getName() + ".data.stats.nodamageticks", p.getNoDamageTicks());
    	config.set(p.getName() + ".data.state.remainingair", p.getRemainingAir());
    	config.set(p.getName() + ".data.state.falldistance", Float.toString(p.getFallDistance()));
    	config.set(p.getName() + ".data.state.fireticks", p.getFireTicks());
    	
    	// Save it all
    	ConfigurationManager.save(Config.PLAYERS);
    }

    public static void restore(Player p) {
    	FileConfiguration config = ConfigurationManager.get(Config.PLAYERS);
    	
    	// Reload the config
    	ConfigurationManager.reload(Config.PLAYERS);
    	
    	// Inventory
    	p.getInventory().setContents(config.getList(p.getName() + ".data.inv.main").toArray(new ItemStack[0]));
    	p.getInventory().setArmorContents(config.getList(p.getName() + ".data.inv.armor").toArray(new ItemStack[0]));
    	
    	// Health
    	p.setHealth(config.getInt(p.getName() + ".data.health"));
    	
    	// Hunger
    	p.setFoodLevel(config.getInt(p.getName() + ".data.hunger.foodlevel"));
    	p.setSaturation(Float.parseFloat(config.getString(p.getName() + ".data.hunger.saturation")));
    	p.setExhaustion(Float.parseFloat(config.getString(p.getName() + ".data.hunger.exhaustion")));
    	
    	// Experience
    	p.setLevel(config.getInt(p.getName() + ".data.exp.level"));
    	p.setExp(Float.parseFloat(config.getString(p.getName() + ".data.exp.ammount")));
    	
    	// GameMode
    	p.setGameMode(GameMode.getByValue(config.getInt(p.getName() + ".data.gamemode")));
    	
    	// Flying
    	p.setAllowFlight(config.getBoolean(p.getName() + ".data.flight.allowed"));
    	p.setFlying(config.getBoolean(p.getName() + ".data.flight.flying"));
    	
    	// Locations
    	p.teleport(Util.locationFromString(config.getString(p.getName() + ".data.location")), TeleportCause.PLUGIN);
    	
    	// Sleep
    	p.setSleepingIgnored(config.getBoolean(p.getName() + ".data.sleepignored"));
    	
    	// Information
    	p.setDisplayName(config.getString(p.getName() + ".data.info.displayname"));
    	p.setPlayerListName(config.getString(p.getName() + ".data.info.listname"));
    	
    	// Statistics
    	p.setTicksLived(config.getInt(p.getName() + ".data.stats.tickslived"));
    	p.setNoDamageTicks(config.getInt(p.getName() + ".data.stats.nodamageticks"));
    	
    	// State
    	p.setRemainingAir(config.getInt(p.getName() + ".data.state.remainingair"));
    	p.setFallDistance(Float.parseFloat(config.getString(p.getName() + ".data.state.falldistance")));
    	p.setFireTicks(config.getInt(p.getName() + ".data.state.fireticks"));
    }
    
    public static void reset(Player p/**, Battle b**/) {
    	Util.clearInventory(p);
    	p.setHealth(p.getMaxHealth());
    	p.setFoodLevel(16);
    	p.setSaturation(1000);
    	p.setExhaustion(0);
    	p.setLevel(0);
    	p.setExp(0);
    	p.setGameMode(GameMode.SURVIVAL);
    	p.setAllowFlight(false);
    	p.setFlying(false);
    	p.setSleepingIgnored(true);
    	//TODO p.setDisplayName(ChatColor.GRAY + "[BN] " + team.getChatColor() + p.getName() + ChatColor.RESET);
    	//TODO Util.setPlayerListName(p, t);
    	p.setTicksLived(1);
    	p.setNoDamageTicks(0);
    	p.setRemainingAir(300);
    	p.setFallDistance(0.0f);
    	p.setFireTicks(-20);
    }

    public static void addKill(Player killer) {
    	ConfigurationManager.get(Config.PLAYERS).set(killer.getName() + ".stats.kills", getKills(killer) + 1);
    	ConfigurationManager.save(Config.PLAYERS);
    }

    public static void addDeath(Player victom) {
    	ConfigurationManager.get(Config.PLAYERS).set(victom.getName() + ".stats.deaths", getDeaths(victom) + 1);
    	ConfigurationManager.save(Config.PLAYERS);
    }

    public static int getKills(Player p) {
        return deObfuscate(ConfigurationManager.get(Config.PLAYERS).getString(".stats.kills"));
    }

    public static int getDeaths(Player p) {
    	return deObfuscate(ConfigurationManager.get(Config.PLAYERS).getString(".stats.deaths"));
    }

    public double getKDRatio(Player p) {
        return getKills(p) / getDeaths(p);
    }

    
    ////////////////////
    //      Util      //
    ////////////////////
    
    private static final String obfuscate(int i) {
        return toBinaryString(i).replace("0", "A").replace("1", "B");
    }

    private static final int deObfuscate(String i) {
        return parseInt(i.replace("A", "0").replace("B", "1"), 2);
    }
    
} 
