package me.limebyte.battlenight.core.Battle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Exceptions.AlreadyInBattleException;
import me.limebyte.battlenight.core.Exceptions.BattleInProgressException;
import me.limebyte.battlenight.core.Exceptions.NotInBattleException;

/**
 * Represents a Battle
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Battle {
    
    private Arena arena;
    private List<String> players = new ArrayList<String>();
    
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
        inProgress = true;
    }
    
    public void stop() {
    	inProgress = false;
    }
    
    /**
     * 
     * @param player
     * @throws BattleInProgressException
     * @throws AlreadyInBattleException
     */
    public void addPlayer(Player player) {
        if (this.isInProgress()) throw new BattleInProgressException(player);
        if (this.containsPlayer(player)) throw new AlreadyInBattleException(player);
        
        players.add(player.getName());
        plugin.getPlayerData().save(player);
    }
    
    /**
     * 
     * @param player
     * @throws NotInBattleException
     */
    public void removePlayer(Player player) throws NotInBattleException {
        if (!this.containsPlayer(player)) throw new NotInBattleException(player);
        
        players.remove(player.getName());
        plugin.getPlayerData().restore(player);
    }
    
    public boolean containsPlayer(Player player) {
    	for (String p : players) {
    		if (player == plugin.getServer().getPlayerExact(p)) return true;
    	}
    	
    	return false;
    }
    
    ////////////////////
    //    Getters     //
    ////////////////////
    
    public Arena getCurrentArena() {
		return arena;
    }
    
    public List<Player> getPlayers() {
    	List<Player> playerList = new ArrayList<Player>();
    	for (String p : players) {
    		playerList.add(plugin.getServer().getPlayerExact(p));
    	}
		return playerList;
    }
    
    public boolean isInProgress() {
        return inProgress;
    }
}
