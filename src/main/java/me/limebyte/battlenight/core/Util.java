package me.limebyte.battlenight.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import me.limebyte.battlenight.core.managers.PlayerManager;
import me.limebyte.battlenight.core.managers.TrackManager;
import me.limebyte.battlenight.core.managers.TrackManager.Track;
import me.limebyte.battlenight.core.Battle.Team;
import me.limebyte.battlenight.core.Battle.classes.ArmourType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public final class Util {
	
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
    
    public static void tellPlayer(Player p, Track t) {
        p.sendMessage(t.getMessage());
    }
    
    public static void tellPlayer(Player p, Track t, String... args) {
        p.sendMessage(t.getMessage(args));
    }
    
    public static void tellPlayers(Player[] p, TrackManager.Track t) {
        for (Player aP : p) {
            aP.sendMessage(t.getMessage());
        }
    }
    
    ////////////////////
    //  Battle Util   //
    ////////////////////
    
   public static void preparePlayer(Player p) {
	   PlayerManager.save(p);
	   PlayerManager.reset(p);
   }
   
   public static void restorePlayer(Player p) {
	   PlayerManager.reset(p);
	   PlayerManager.restore(p);
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
   
   public static void clearInventory(Player player) {
	   PlayerInventory inv = player.getInventory();
	   inv.clear();
	   inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
   }
   
   public static List<ItemStack> sortArmour(List<ItemStack> armour) {
	   ItemStack helmet = null, chestplate = null, leggings = null, boots = null;
	   for (ItemStack stack : armour) {
		   if		(ArmourType.HELMET.contains(stack))		helmet = stack;
		   else if	(ArmourType.CHESTPLATE.contains(stack))	chestplate = stack;
		   else if	(ArmourType.LEGGINGS.contains(stack))	leggings = stack;
		   else if	(ArmourType.BOOTS.contains(stack))		boots = stack;
	   }
	   
	   List<ItemStack> sorted = new ArrayList<ItemStack>();
		   sorted.add(0, helmet);
		   sorted.add(1, chestplate);
		   sorted.add(2, leggings);
		   sorted.add(3, boots);
	   
	   return sorted;
   }
   
   
   ////////////////////
   // Configuration  //
   ////////////////////
   
   public static void copyFile(InputStream in, File file) {
       try {
           OutputStream out = new FileOutputStream(file);
           byte[] buf = new byte[1024];
           int len;
           while((len=in.read(buf))>0){
               out.write(buf,0,len);
           }
           out.close();
           in.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
