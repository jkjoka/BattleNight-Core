package me.limebyte.battlenight.core.Battle.Arenas;

import java.util.HashMap;

import org.bukkit.Location;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Arenas.Waypoint;
import me.limebyte.battlenight.core.Battle.Arenas.Waypoint.WaypointType;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;

/**
 * Represents a saved Arena.
 */
public class Arena {
    
    // Get Main Class
    public static BattleNight plugin;
    public Arena(BattleNight instance) {
        plugin = instance;
    }

    private String name;
    private HashMap<String, Waypoint> waypoints = new HashMap<String, Waypoint>();
    private static final Config configFile = Config.ARENAS;
    
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
    public Waypoint getWaypoint(String name) {
        return waypoints.get(name);
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
    public void setWaypoint(String name, WaypointType type, Location location) {
        if (waypoints.containsKey(name)) {
        	waypoints.get(name).setLocation(location);
        }
        else {
        	waypoints.put(name, new Waypoint(type, this));
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
    
    /**
     * Checks if the arena is setup.
     * 
     * @return Whether it is setup.
     */
    public boolean isSetup() {
    		int spawnCount = 0;
    		
    		for (Waypoint wp : waypoints.values()) {
    			switch (wp.getType()) {
    				case SPAWN: {
    					if (wp.isSet()) spawnCount++;
    					break;
    				}
    				case LOUNGE: {
    					if (!wp.isSet()) return false;
    					break;
    				}
    				case SPECTATOR: {
    					if (!wp.isSet()) return false;
    					break;
    				}
    				default: break;
    			}
    		}
    		
    		if (spawnCount >= BattleNight.getBattle().getMode().getNumTeams()) return true;
    		else return false;
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
