package me.limebyte.battlenight.core.Battle;

import org.bukkit.Location;

import me.limebyte.battlenight.core.Battle.Waypoint.WaypointType;

/**
 * Represents a Arena.
 * 
 * @author LimeByte
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Arena {
    
    private String name;
    private String displayName;
    private Waypoint aSpawn = new Waypoint(WaypointType.ASPAWN, this);
    private Waypoint bSpawn = new Waypoint(WaypointType.BSPAWN, this);
    private boolean enabled = true;
    
    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Constructs a new BattleNight Arena.
     * 
     * @param name The simple name.
     */
    public Arena(String name) {
        this.name = name;
    }

    ////////////////////
    //    Getters     //
    ////////////////////
    
    /**
     * Gets the simple name for the Arena.
     * 
     * @return The simple name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the "friendly" name for the Arena.
     * 
     * @return The current display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the {@link Location} for the A {@link Team} to spawn in the Arena.
     * 
     * @return The current A team spawn location.
     */
    public Location getASpawn() {
        return aSpawn.getLocation();
    }

    /**
     * Gets the {@link Location} for the B {@link Team} to spawn in the Arena.
     * 
     * @return The current B team spawn location.
     */
    public Location getBSpawn() {
        return bSpawn.getLocation();
    }
    

    ////////////////////
    //    Setters     //
    ////////////////////
    
    /**
     * Sets the "friendly" name for the Arena.
     * 
     * @param The new display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the {@link Location} for the A {@link Team} to spawn in the Arena.
     * 
     * @param The new A team spawn location.
     */
    public void setASpawn(Location location) {
        aSpawn.setLocation(location);
    }

    /**
     * Sets the {@link Location} for the B {@link Team} to spawn in the Arena.
     * 
     * @param The new B team spawn location.
     */
    public void setBSpawn(Location location) {
        bSpawn.setLocation(location);
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
     * 
     * @return Whether it is setup.
     */
    public boolean isSetup() {
        return (aSpawn.isSet() && bSpawn.isSet());
    }
    
    /**
     * Checks if the Arena is enabled.
     * 
     * @return Whether it is enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

}
