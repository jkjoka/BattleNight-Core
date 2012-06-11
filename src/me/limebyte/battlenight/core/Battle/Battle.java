package me.limebyte.battlenight.core.Battle;

import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Team.TeamColor;
import me.limebyte.battlenight.core.Exceptions.BattleInProgressException;

/**
 * Represents a Battle
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Battle {
	
    public Team teamA = new Team("Red", TeamColor.RED);
    public Team teamB = new Team("Blue", TeamColor.BLUE);
    
    private Arena arena;
    private Player[] players;
    
    private boolean inProgress = false;

    // Get Main Class
    public static BattleNight plugin;
    public Battle(BattleNight instance) {
        plugin = instance;
    }
    
    ////////////////////
    //    Methods     //
    ////////////////////
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    public void addPlayer(Player player) throws BattleInProgressException {
        if (this.isInProgress()) throw new BattleInProgressException();
    }
    
    ////////////////////
    //    Getters     //
    ////////////////////
    
    public Arena getCurrentArena() {
		return arena;
    }
    
    public Player[] getPlayers() {
		return players;
    }
    
    public Team getTeam(Player p) {
    	if (teamA.getPlayers().contains(p)) return teamA;
    	else if (teamB.getPlayers().contains(p)) return teamB;
    	else return null;
    }
    
    public boolean isInProgress() {
        return inProgress;
    }
}
