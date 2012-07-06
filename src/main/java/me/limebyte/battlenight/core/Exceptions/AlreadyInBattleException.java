package me.limebyte.battlenight.core.Exceptions;

import org.bukkit.entity.Player;

import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.Tracks.Track;

/**
 * Represents a AlreadyInBattleException.
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
@SuppressWarnings("serial")
public class AlreadyInBattleException extends RuntimeException {
    
    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Constructs a new AlreadyInBattleException.
     */
    public AlreadyInBattleException(Player player) {
        Util.tellPlayer(player, Track.PLAYER_NOT_FOUND);
        return;
    }
    
}
