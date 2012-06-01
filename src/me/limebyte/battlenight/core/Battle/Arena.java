package me.limebyte.battlenight.core.Battle;

import org.bukkit.Location;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Waypoint.WaypointType;

/**
 * Represents a Arena.
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Arena {
    
    private String name;
    private String displayName;
    private Waypoint redSpawn = new Waypoint(WaypointType.REDSPAWN, this);
    private Waypoint blueSpawn = new Waypoint(WaypointType.BLUESPAWN, this);
    private boolean enabled = true;

    // Get Main Class
    public static BattleNight plugin;
    public Arena(BattleNight instance) {
        plugin = instance;
    }
    
    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Create a new arena by specifying the name for this Arena.
     */
    public Arena(String name) {
        name = this.name;
    }

    ////////////////////
    //    Getters     //
    ////////////////////
    
    /**
     * Gets the name of this Arena.
     * @return the current name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the "friendly" name of the Arena.
     * @return the current display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the Arena's Location for the red team to spawn.
     * @return the current red spawn location
     */
    public Location getRedSpawn() {
        return redSpawn.getLocation();
    }

    /**
     * Gets the Arena's Location for the blue team to spawn.
     * @return the current blue spawn location
     */
    public Location getBlueSpawn() {
        return blueSpawn.getLocation();
    }
    

    ////////////////////
    //    Setters     //
    ////////////////////
    
    /**
     * Sets the "friendly" name of the Arena.
     * @param the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the Arena's Location for the red team to spawn.
     * @param the new red spawn location
     */
    public void setRedSpawn(Location location) {
        redSpawn.setLocation(location);
    }

    /**
     * Sets the Arena's Location for the blue team to spawn.
     * @param the new blue spawn location
     */
    public void setBlueSpawn(Location location) {
        blueSpawn.setLocation(location);
    }
    
    /**
     * Sets the Arena's status to enabled.
     */
    public void enable() {
        enabled = true;
    }
    
    /**
     * Sets the Arena's status to disabled.
     */
    public void disable() {
        enabled = false;
    }

    ////////////////////
    //    Checkers    //
    ////////////////////
    
    /**
     * Checks if the Arena is setup.
     * @return whether it is setup
     */
    public boolean isSetup() {
        return (redSpawn.isSet() && blueSpawn.isSet());
    }
    
    /**
     * Checks if the Arena is enabled.
     * @return whether it is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

}
