package me.limebyte.battlenight.core.Battle;

import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Team.TeamColor;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Battle {
	
    public Team teamA = new Team("Red", TeamColor.RED);
    public Team teamB = new Team("Blue", TeamColor.BLUE);
    
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
    	if (teamA.getPlayers().contains(p)) return teamA;
    	else if (teamB.getPlayers().contains(p)) return teamB;
    	else return null;
    }
}
