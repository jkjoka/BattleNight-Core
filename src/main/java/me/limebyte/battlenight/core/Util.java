package me.limebyte.battlenight.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import me.limebyte.battlenight.core.Battle.Team;
import me.limebyte.battlenight.core.Battle.Classes.ArmorType;
import me.limebyte.battlenight.core.Configuration.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public final class Util {
    
    // Get Main Class
    public static BattleNight plugin;
    public Util(BattleNight instance) {
        plugin = instance;
    }
	
    public static String locationToString(Location loc) {
    	String w = loc.getWorld().getName();
    	double x = loc.getBlockX() + 0.5;
    	int y = loc.getBlockY();
    	double z = loc.getBlockZ() + 0.5;
    	return w + "," + x + "," + y + "," + z;
    }
    
    public static Location locationFromString(String s) {
    	String part[] = s.split(",");
    	World w = Bukkit.getServer().getWorld(part[0]);
    	double x = Double.parseDouble(part[1]);
    	int y = Integer.parseInt(part[2]);
    	double z = Double.parseDouble(part[3]);
    	return new Location(w, x, y, z);
    }
	
    
    ////////////////////
    //  Chat Related  //
    ////////////////////
    
    public static void tellPlayer(Player p, Tracks.Track t) {
        p.sendMessage(t.getMessage());
    }
    
    public static void tellPlayers(Player[] p, Tracks.Track t) {
        for (Player aP : p) {
            aP.sendMessage(t.getMessage());
        }
    }
    
    ////////////////////
    //  Battle Util   //
    ////////////////////
    
   public void preparePlayer(Player p, Team t, Location destination) {
	   PlayerData pd = plugin.getPlayerData();
	   pd.save(p);
	   pd.reset(p, t, destination);
   }
   
   public void restorePlayer(Player p, Team t, Location destination) {
	   PlayerData pd = plugin.getPlayerData();
	   pd.reset(p, t, destination);
	   pd.restore(p);
   }
   
   ////////////////////
   // PlayerListName //
   ////////////////////
   
   public static void setPlayerListName(Player p, Team t) {
	   String pListName = "�7[BN] " + p.getName();
	   p.setPlayerListName((pListName.length() < 16) ? pListName : pListName.substring(0, 16));
   }
   
   ////////////////////
   //     Items      //
   ////////////////////
   
   public static List<ItemStack> sortArmor(List<ItemStack> armor) {
	   ItemStack helmet = null, chestplate = null, leggings = null, boots = null;
	   for (ItemStack stack : armor) {
		   if		(ArmorType.HELMET.contains(stack))		helmet = stack;
		   else if	(ArmorType.CHESTPLATE.contains(stack))	chestplate = stack;
		   else if	(ArmorType.LEGGINGS.contains(stack))	leggings = stack;
		   else if	(ArmorType.BOOTS.contains(stack))		boots = stack;
	   }
	   
	   List<ItemStack> sorted = new ArrayList<ItemStack>();
		   sorted.set(1, helmet);
		   sorted.set(2, chestplate);
		   sorted.set(3, leggings);
		   sorted.set(4, boots);
	   
	   return sorted;
   }
   
   // Config
   
   public static void copyFile(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
