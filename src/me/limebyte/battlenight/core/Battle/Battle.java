package me.limebyte.battlenight.core.Battle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.PlayerData;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Battle {
	
    public Team redTeam = new Team("Red", ChatColor.RED);
    public Team blueTeam = new Team("Blue", ChatColor.BLUE);
    PlayerData playerData = plugin.getPlayerData();
    Config config = plugin.config;
    
    Arena[] arenas;
    Player[] players;

    // Get Main Class
    public static BattleNight plugin;
    public Battle(BattleNight instance) {
        plugin = instance;
    }
    
    public Arena[] getArenas() {
		return arenas;
    }
    
    public Player[] getPlayers() {
		return players;
    }
    
    public Team getTeam(Player p) {
    	if (blueTeam.getPlayers().contains(p)) return blueTeam;
    	else if (redTeam.getPlayers().contains(p)) return redTeam;
    	else return null;
    }
}
