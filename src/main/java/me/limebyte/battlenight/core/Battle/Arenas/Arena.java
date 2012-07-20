package me.limebyte.battlenight.core.Battle.Arenas;

import org.bukkit.Location;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Waypoint;
import me.limebyte.battlenight.core.Battle.Waypoint.WaypointType;
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
    private Waypoint aSpawn = new Waypoint(WaypointType.ASPAWN, this);
    private Waypoint bSpawn = new Waypoint(WaypointType.BSPAWN, this);
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
     * Gets the team A spawn waypoint associated with this arena.
     * 
     * @return The waypoint
     */
    public Waypoint getASpawn() {
        return aSpawn;
    }

    /**
     * Gets the team B spawn waypoint associated with this arena.
     * 
     * @return The waypoint.
     */
    public Waypoint getBSpawn() {
        return bSpawn;
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
     * Sets the location for the team A waypoint.
     * 
     * @param location The spawn location.
     */
    public void setASpawn(Location location) {
        aSpawn.setLocation(location);
    }

    /**
     * Sets the location for the team B waypoint.
     * 
     * @param location The spawn location.
     */
    public void setBSpawn(Location location) {
        bSpawn.setLocation(location);
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
        return (aSpawn.isSet() && bSpawn.isSet());
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
