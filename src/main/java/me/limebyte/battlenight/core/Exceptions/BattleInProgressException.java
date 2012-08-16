package me.limebyte.battlenight.core.Exceptions;

import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.managers.TrackManager.Track;

import org.bukkit.entity.Player;

/**
 * Represents a BattleInProgressException.
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
@SuppressWarnings("serial")
public class BattleInProgressException extends RuntimeException {

    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Constructs a new BattleInProgressException.
     */
    public BattleInProgressException(Player player) {
        Util.tellPlayer(player, Track.PLAYER_NOT_FOUND);
        return;
    }
    
}
