package me.limebyte.battlenight.core;

import me.limebyte.battlenight.core.Tracks.Track;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Commands implements CommandExecutor {

    // Get Main Class
    public static BattleNight plugin;
    public Commands(BattleNight instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
    	
    	if (arg[0].equalsIgnoreCase("join")) {
	    	if (Allowed(sender, "user", false)) {
	    		Player player = (Player) sender;
		    	try {
					plugin.getBattle().addPlayer(player);
				} catch (Exception e) {}
	    	}
    	}
    	
    	else if (arg[0].equalsIgnoreCase("leave")) {
    		if (Allowed(sender, "user", false)) {
	    		Player player = (Player) sender;
	    		try {
	    			plugin.getBattle().removePlayer(player);
	    		} catch (Exception e) {}
    		}
    	}
    	
    	else if (arg[0].equalsIgnoreCase("equip")) {
    		if (Allowed(sender, "admin", false)) {
	    		Player player = (Player) sender;
	    		plugin.getClassManager().getClasses().get(1).equip(player);
    		}
    	}
    	
    	else {
    	    sender.sendMessage(Track.INVALID_COMMAND.getMessage());
    	}
    	
        return true;
    }
    
    private boolean Allowed(CommandSender sender, String perm, boolean nonPlayer) {
    	if (sender instanceof Player) {
    		Player player = (Player) sender;
    		if (player.hasPermission("battlenight." + perm)) return true;
    		else Util.tellPlayer(player, Track.NO_PERMISSION);  return false;
    	}
    	else {
    		if (nonPlayer) return true;
    		else sender.sendMessage(Track.PLAYER_ONLY.getMessage());  return false;
    	}
    }
}
