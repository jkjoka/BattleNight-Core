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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
    	
    	if (arg[0].equalsIgnoreCase("join") && Allowed(sender, "user", false)) {
    		Player player = (Player) sender;
    		plugin.battle.redTeam.addPlayer(player);
    		plugin.util.preparePlayer(player, plugin.battle.getTeam(player), player.getLocation());
    	}
    	else if (arg[0].equalsIgnoreCase("leave") && Allowed(sender, "user", false)) {
    		Player player = (Player) sender;
    		plugin.util.restorePlayer(player, plugin.battle.getTeam(player), player.getLocation());
    		plugin.battle.redTeam.removePlayer(player);
    	}
    	
        return true;
    }
    
    private boolean Allowed(CommandSender sender, String perm, boolean nonPlayer) {
    	if (sender instanceof Player) {
    		Player player = (Player) sender;
    		if (player.hasPermission("battlenight." + perm)) {
    			return true;
    		}
    		else {
    			player.sendMessage(Tracks.Track.NO_PERMISSION.getMessage());
    			return false;
    		}
    	}
    	else {
    		if (nonPlayer) {
    			return true;
    		}
    		else {
    			sender.sendMessage(Tracks.Track.PLAYER_ONLY.getMessage());
    			return false;
    		}
    	}
    }
}
