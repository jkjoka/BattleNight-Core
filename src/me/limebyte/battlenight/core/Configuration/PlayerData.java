package me.limebyte.battlenight.core.Configuration;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

import java.util.Arrays;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Team;
import me.limebyte.battlenight.core.Configuration.Config.ConfigFile;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

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
    
    public void reloadPlayer(Player p) {
    	
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    	
    	// Reload the config
    	plugin.config.reload(ConfigFile.PLAYERS);
    	
    	if(config.getConfigurationSection(p.getName()) == null) {
    		config.set(p.getName() + ".stats.kills", obfuscate(0));
    		config.set(p.getName() + ".stats.deaths", obfuscate(0));
        	// Save changes
        	plugin.config.save(ConfigFile.PLAYERS);
    	}
    }
    
    public void save(Player p) {
    	
    	// Reload the config
    	plugin.config.reload(ConfigFile.PLAYERS);
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    	
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
    	config.set(p.getName() + ".data.state.falldistance", Float.toString(p.getFallDistance()));
    	config.set(p.getName() + ".data.state.fireticks", p.getFireTicks());
    	
    	// Save it all
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    public void restore(Player p) {
    	// Reload the config
    	plugin.config.reload(ConfigFile.PLAYERS);
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    	
    	// Inventory
    	p.getInventory().setContents(config.getList(p.getName() + ".data.inv.main").toArray(new ItemStack[p.getInventory().getContents().length]));
    	p.getInventory().setArmorContents(config.getList(p.getName() + ".data.inv.armor").toArray(new ItemStack[p.getInventory().getArmorContents().length]));
    	
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
    	p.teleport(plugin.util.locationFromString(config.getString(p.getName() + ".data.location")), TeleportCause.PLUGIN);
    	
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
    
    public void reset(Player p, Team t, Location destination) {
    	// Inventory
    	p.getInventory().clear();
    	
    	// Health
    	p.setHealth(p.getMaxHealth());
    	
    	// Hunger
    	p.setFoodLevel(16);
    	p.setSaturation(1000);
    	p.setExhaustion(0);
    	
    	// Experience
    	p.setLevel(0);
    	p.setExp(0);
    	
    	// GameMode
    	p.setGameMode(GameMode.SURVIVAL);
    	
    	// Flying
    	p.setAllowFlight(false);
    	p.setFlying(false);
    	
    	// Locations
    	p.teleport(destination, TeleportCause.PLUGIN);
    	
    	// Sleep
    	p.setSleepingIgnored(true);
    	
    	// Information
    	String displayName = ChatColor.GRAY+"[BN] " + t.getChatColor()+"["+t.getName().substring(0, 1)+"] " + ChatColor.WHITE + p.getName();
    	p.setDisplayName(displayName);
    	p.setPlayerListName((displayName.length() < 16) ? displayName : displayName.substring(0, 16));
    	
    	// Statistics
    	p.setTicksLived(1);
    	p.setNoDamageTicks(0);
    	
    	// State
    	//TODO p.setRemainingAir(12345);
    	p.setFallDistance(0);
    	p.setFireTicks(0);
    }

    protected void addKill(Player killer) {
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    	config.set(killer.getName() + ".stats.kills", getKills(killer) + 1);
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    protected void addDeath(Player victom) {
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
    	config.set(victom.getName() + ".stats.deaths", getDeaths(victom) + 1);
    	plugin.config.save(ConfigFile.PLAYERS);
    }

    public int getKills(Player p) {
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
        return deObfuscate(config.getString(".stats.kills"));
    }

    public int getDeaths(Player p) {
    	FileConfiguration config = plugin.config.get(ConfigFile.PLAYERS);
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