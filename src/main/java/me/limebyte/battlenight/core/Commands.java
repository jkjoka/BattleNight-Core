package me.limebyte.battlenight.core;

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
    	
    	if (arg[0].equalsIgnoreCase("join") && Allowed(sender, "user", false)) {
	    	Player player = (Player) sender;
	    	try {
				plugin.getBattle().addPlayer(player);
			} catch (Exception e) {}
    	}
    	
    	else if (arg[0].equalsIgnoreCase("leave") && Allowed(sender, "user", false)) {
    		Player player = (Player) sender;
    		try {
    			plugin.getBattle().removePlayer(player);
    		} catch (Exception e) {}
    	}
    	
    	else if (arg[0].equalsIgnoreCase("start") && Allowed(sender, "admin", true)) {
    		plugin.getBattle().start();
    	}
    	
    	else if (arg[0].equalsIgnoreCase("stop") && Allowed(sender, "admin", true)) {
    		plugin.getBattle().stop();
    	}
    	
    	else if (arg[0].equalsIgnoreCase("equip") && Allowed(sender, "user", true)) {
    		Player player = (Player) sender;
    		plugin.getClassManager().getClasses().get(1).equip(player);
    	}
    	
    	else {
    	    // Invalid Command
    	}
    	
        return true;
    }
    
    private boolean Allowed(CommandSender sender, String perm, boolean nonPlayer) {
    	if (sender instanceof Player) {
    		Player player = (Player) sender;
    		if (player.hasPermission("battlenight." + perm)) return true;
    		else player.sendMessage(Tracks.Track.NO_PERMISSION.getMessage());  return false;
    	}
    	else {
    		if (nonPlayer) return true;
    		else sender.sendMessage(Tracks.Track.PLAYER_ONLY.getMessage());  return false;
    	}
    }
}
