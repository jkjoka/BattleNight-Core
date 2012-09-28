package me.limebyte.battlenight.core.Battle.arenas;

import org.bukkit.Location;

import me.limebyte.battlenight.core.Battle.arenas.Waypoint;
import me.limebyte.battlenight.core.Battle.arenas.Waypoint.WaypointType;
import me.limebyte.battlenight.core.configuration.Config;
import me.limebyte.battlenight.core.configuration.ConfigurationManager;

/**
 * Represents a saved Arena.
 *
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Arena {

    private String name;
    private static final Config configFile = Config.ARENAS;
    
    private Waypoint aSpawn = new Waypoint(WaypointType.ASPAWN, this);
    private Waypoint bSpawn = new Waypoint(WaypointType.BSPAWN, this);
    private Waypoint spectator = new Waypoint(WaypointType.SPECTATOR, this);
    
    private static Waypoint lounge = new Waypoint(WaypointType.LOUNGE);
    
    ////////////////////
    //  Constructors  //
    ////////////////////
    
    /**
     * Creates an arena with the specified name.  The constructor merely determines the name;
     * to set the actual spawn points and the display name, you'll need to call the
     * appropriate methods.
     * 
     * @param name The simple name for the arena.
     * @see Arena#setASpawn(Location)
     * @see Arena#setBSpawn(Location)
     * @see Arena#setDisplayName(String)
     */
    public Arena(String name) {
        this.name = name.toLowerCase();
    }

    ////////////////////
    //    Getters     //
    ////////////////////
    
    /**
     * Gets the simple name associated with this arena.
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the "friendly" name associated with this arena.  This may include colour.
     * 
     * @return The display name
     */
    public String getDisplayName() {
    	ConfigurationManager.reload(configFile);
        return ConfigurationManager.get(configFile).getString(name + ".displayname", name);
    }

    /**
     * Gets the waypoint associated with this name.
     * 
     * @return The Waypoint
     */
    public Waypoint getWaypoint(WaypointType type) {
    	switch (type) {
    		case ASPAWN: {
    			return aSpawn;
    		}
    		case BSPAWN: {
    			return bSpawn;
    		}
    		case SPECTATOR: {
    			return spectator;
    		}
    		case LOUNGE: {
    			return lounge;
    		}
    		default: return null;
    	}
    }
    

    ////////////////////
    //    Setters     //
    ////////////////////
    
    /**
     * Sets the "friendly" name associated with this arena.  This may include colour.
     * 
     * @param displayName The new display name
     */
    public void setDisplayName(String displayName) {
    	ConfigurationManager.get(configFile).set(name + ".displayname", displayName);
    	ConfigurationManager.save(configFile);
    }

    /**
     * Creates a new Waypoint if non-existent and sets the location.
     * 
     * @param location The spawn location.
     */
    public void setWaypoint(WaypointType type, Location location) {
    	if (getWaypoint(type) != null) {
    		getWaypoint(type).setLocation(location);
    	}
    }
    
    /**
     * Sets the arena's status to enabled.
     * 
     * @see Arena#disable()
     * @see Arena#isEnabled()
     */
    public void enable() {
        ConfigurationManager.get(configFile).set(name + ".enabled", true);
        ConfigurationManager.save(configFile);
    }
    
    /**
     * Sets the arena's status to disabled.
     * 
     * @see Arena#enable()
     * @see Arena#isEnabled()
     */
    public void disable() {
        ConfigurationManager.get(configFile).set(name + ".enabled", false);
        ConfigurationManager.save(configFile);
    }

    ////////////////////
    //    Checkers    //
    ////////////////////
    
    public boolean isSetup(WaypointType type) {
    	if (getWaypoint(type) != null) {
    		if (getWaypoint(type).isSet()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Checks if the arena is setup.
     * 
     * @return Whether it is setup.
     */
    public boolean isSetup() {
    	for (WaypointType type : WaypointType.values()) {
    		if (!isSetup(type)) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if the arena is enabled.
     * 
     * @return Whether it is enabled.
     * @see Arena#enable()
     * @see Arena#disable()
     */
    public boolean isEnabled() {
    	ConfigurationManager.reload(configFile);
        return ConfigurationManager.get(configFile).getBoolean(name + ".enabled", true);
    }

}
