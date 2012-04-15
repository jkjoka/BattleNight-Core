package me.limebyte.battlenight.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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
	
    public void tellPlayer(Player p, Tracks.Track t) {
        p.sendMessage(t.getMessage());
    }
    
    public void tellPlayers(Player[] p, Tracks.Track t) {
        for (Player aP : p) {
            aP.sendMessage(t.getMessage());
        }
    }
}
