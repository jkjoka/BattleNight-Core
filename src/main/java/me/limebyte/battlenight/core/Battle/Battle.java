package me.limebyte.battlenight.core.Battle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.Battle.arenas.Arena;
import me.limebyte.battlenight.core.Battle.arenas.Waypoint.WaypointType;
import me.limebyte.battlenight.core.Battle.modes.BattleMode;
import me.limebyte.battlenight.core.exceptions.AlreadyInBattleException;
import me.limebyte.battlenight.core.exceptions.BattleInProgressException;
import me.limebyte.battlenight.core.exceptions.NotInBattleException;
import me.limebyte.battlenight.core.exceptions.WaypointsNotSetException;

/**
 * Represents a Battle
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Battle {
    
    private Arena arena;
    private BattleMode mode;
    private List<String> players = new ArrayList<String>();
    
    private boolean inProgress = false;
    
    public Battle(BattleMode mode) {
    	this.mode = mode;
    	arena = new Arena("default");
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
    	if (!arena.isSetup(WaypointType.LOUNGE)) throw new WaypointsNotSetException(player);
        if (this.isInProgress()) throw new BattleInProgressException(player);
        if (this.containsPlayer(player)) throw new AlreadyInBattleException(player);
        
        players.add(player.getName());
        Util.preparePlayer(player);
        player.teleport(arena.getWaypoint(WaypointType.LOUNGE).getLocation(), TeleportCause.PLUGIN);
    }
    
    /**
     * 
     * @param player
     * @throws NotInBattleException
     */
    public void removePlayer(Player player) throws NotInBattleException {
        if (!this.containsPlayer(player)) throw new NotInBattleException(player);
        
        players.remove(player.getName());
        Util.restorePlayer(player);
    }
    
    public boolean containsPlayer(Player player) {
    	return players.contains(player.getName());
    }
    
    ////////////////////
    //    Getters     //
    ////////////////////
    
    public Arena getArena() {
		return arena;
    }
    
    public BattleMode getMode() {
		return mode;
    }
    
    public List<String> getPlayers() {
		return players;
    }
    
    public boolean isInProgress() {
        return inProgress;
    }
}
