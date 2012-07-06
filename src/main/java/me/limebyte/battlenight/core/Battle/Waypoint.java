package me.limebyte.battlenight.core.Battle;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Util;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;

import org.bukkit.Location;

/**
 * Represents a saved waypoint for general use or for an Arena.
 */
public class Waypoint {

    // Get Main Class
    public static BattleNight plugin;
    public Waypoint(BattleNight instance) {
        plugin = instance;
    }
	
    private Arena arena;
    private WaypointType type;
    private static final ConfigurationManager cm = plugin.getConfigManager();
    private static final Config configFile = Config.WAYPOINTS;

    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Creates a waypoint based on the specified type.  The constructor merely determines the
     * type; to set the actual location, you'll need to call the appropriate methods.
     * 
     * @param type The type you want the waypoint to be.
     * @see Waypoint#setLocation(Location)
     */
    public Waypoint(WaypointType type) {
        this.type = type;
        this.arena = null;
    }
    
    /**
     * Creates a waypoint based on the specified type for the specified arena.  The constructor
     * merely determines the type and the arena; to set the actual location, you'll need to
     * call the appropriate methods.
     * 
     * @param type The type you want the waypoint to be.
     * @param arena The arena you want the waypoint for.
     * @see Waypoint#setLocation(Location)
     */
    public Waypoint(WaypointType type, Arena arena) {
    	this.type = type;
        this.arena = arena;
    }

    ////////////////////
    //    Getters     //
    ////////////////////
    
    /**
     * Gets the arena associated with this waypoint.  This may return null if it is a general
     * waypoint.
     * 
     * @return The arena
     */
    public Arena getArena() {
        return arena;
    }
    
    /**
     * Gets the type of waypoint.
     * 
     * @return The type
     */
    public WaypointType getType() {
        return type;
    }
    
    /**
     * Gets the location for this waypoint.  This may return null if it is not set.
     * 
     * @return The location
     * @see Waypoint#isSet()
     * @see Waypoint#setLocation(Location)
     */
    public Location getLocation() {
        // Reload the configuration
        cm.reload(configFile);
        
        // Get the location string
        String loc;
        if (arena != null) {
        	loc = cm.get(configFile).getString(arena.getName() + "." + type.getName());
        }
        else {
        	loc = cm.get(configFile).getString("general." + type.getName());
        }
        
        // Convert it to a Location
        return Util.locationFromString(loc);
    }
    
    ////////////////////
    //    Setters     //
    ////////////////////
    
    /**
     * Sets the location for the waypoint.
     * 
     * @param location The location to set.
     * @see Waypoint#isSet()
     * @see Waypoint#getLocation()
     */
    public void setLocation(Location location) {
        // Convert it to a String
        String loc = Util.locationToString(location);
        
        // Set the location string
        if (arena != null) {
        	cm.get(configFile).set(arena.getName() + "." + type.getName(), loc);
        }
        else {
        	cm.get(configFile).set("general." + type.getName(), loc);
        }
        
        // Save the configuration
        cm.save(configFile);
    }

    ////////////////////
    //    Checkers    //
    ////////////////////
    
    /**
     * Checks if the waypoint's location is set.
     * 
     * @return Whether it is set.
     * @see Waypoint#getLocation()
     * @see Waypoint#setLocation(Location)
     */
    public boolean isSet() {
        return getLocation() != null;
    }

    /**
     * Represents various types of waypoints.
     */
    public enum WaypointType {
        ASPAWN,
        BSPAWN,
        LOUNGE,
        SPECTATOR,
        EXIT;
        
        public String getName() {
            return this.name().toLowerCase();
        }
    }
}

