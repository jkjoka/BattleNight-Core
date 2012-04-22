package me.limebyte.battlenight.core.Battle;

import me.limebyte.battlenight.core.BattleNight;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Arena {
    private String name;
    private Waypoints waypoints = new Waypoints(plugin, this);
    private boolean inUse = false;

    // Get Main Class
    public static BattleNight plugin;
    
    /**
     * Create a new arena by specifying the instance of
     * BattleNight and the name for this Arena.
     */
    public Arena(BattleNight instance, String name) {
        plugin = instance;
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    public Waypoints getWaypoints() {
        return waypoints;
    }

    public boolean isInUse() {
        return inUse;
    }
    
    public boolean isSetup() {
        for(Waypoints.Waypoint w : Waypoints.Waypoint.values()) {
            if(!waypoints.isSet(w)) return false;
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
