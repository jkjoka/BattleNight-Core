package me.limebyte.battlenight.core;

import me.limebyte.battlenight.core.Battle.Team;
import me.limebyte.battlenight.core.Configuration.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Util {
    
    // Get Main Class
    public static BattleNight plugin;
    public Util(BattleNight instance) {
        plugin = instance;
    }
    
    Config config = plugin.config;
	
    public String locationToString(Location loc) {
    	String w = loc.getWorld().getName();
    	double x = loc.getX();
    	double y = loc.getY();
    	double z = loc.getZ();
    	float yaw = loc.getYaw();
    	float pitch = loc.getPitch();
    	return w + "|" + x + "|" + y + "|" + z + "|" + yaw + "|" + pitch;
    }
    
    public Location locationFromString(String s) {
    	String part[] = s.split("|");
    	World w = Bukkit.getServer().getWorld(part[0]);
    	double x = Double.parseDouble(part[1]);
    	double y = Double.parseDouble(part[2]);
    	double z = Double.parseDouble(part[3]);
    	float yaw = Float.parseFloat(part[4]);
    	float pitch = Float.parseFloat(part[5]);
    	return new Location(w, x, y, z, yaw, pitch);
    }
	
    
    ////////////////////
    //  Chat Related  //
    ////////////////////
    
    public void tellPlayer(Player p, Tracks.Track t) {
        p.sendMessage(t.getMessage());
    }
    
    public void tellPlayers(Player[] p, Tracks.Track t) {
        for (Player aP : p) {
            aP.sendMessage(t.getMessage());
        }
    }
    
    ////////////////////
    //  Battle Util   //
    ////////////////////
    
    public void preparePlayer(Player p, Team t, Location destination) {
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
    	p.setDisplayName(ChatColor.GRAY+"[BN] " + t.getChatColor()+"["+t.getName()+"] " + ChatColor.WHITE + p.getName());
    	//TODO p.setPlayerListName("");
    	
    	// Statistics
    	p.setTicksLived(0);
    	p.setNoDamageTicks(0);
    	
    	// State
    	//TODO p.setRemainingAir(12345);
    	p.setFallDistance(0);
    	p.setFireTicks(0);
    }
    
    
}
