package me.limebyte.battlenight.core.Battle;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config;

import org.bukkit.Location;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Waypoint {

    private Arena arena;
    private WaypointType type;
    private static Config configFile = Config.WAYPOINTS;

    // Get Main Class
    public static BattleNight plugin;
    public Waypoint(BattleNight instance) {
        plugin = instance;
    }

    ////////////////////
    //  Constructors  //
    ////////////////////
    
    public Waypoint(WaypointType type) {
        type = this.type;
        arena = null;
    }
    
    public Waypoint(WaypointType type, Arena arena) {
        type = this.type;
        arena = this.arena;
    }

    ////////////////////
    //    Getters     //
    ////////////////////
    
    public Arena getArena() {
        return arena;
    }
    
    public WaypointType getType() {
        return type;
    }
    
    public Location getLocation() {
        // Reload the configuration
        plugin.getConfigManager().reload(configFile);
        
        // Get the location String
        String loc;
        if (arena != null) loc = plugin.getConfigManager().get(configFile).getString(arena.getName()+"."+type.getName());
        else loc = plugin.getConfigManager().get(configFile).getString("general."+type.getName());
        
        // Convert it to a Location
        return plugin.util.locationFromString(loc);
    }
    
    ////////////////////
    //    Setters     //
    ////////////////////
    
    public void setLocation(Location location) {
        // Convert it to a String
        String loc = plugin.util.locationToString(location);
        if (arena != null) plugin.getConfigManager().get(configFile).set(arena+"."+type.getName(), loc);
        else plugin.getConfigManager().get(configFile).set("general."+type.getName(), loc);
        
        // Save the configuration
        plugin.getConfigManager().save(configFile);
    }

    ////////////////////
    //    Checkers    //
    ////////////////////
    
    public boolean isSet() {
        return getLocation() != null;
    }

    public enum WaypointType {
        ALOUNGE,
        BLOUNGE,
        ASPAWN,
        BSPAWN,
        EXIT;
        
        public String getName() {
            return this.name().toLowerCase();
        }
    }
}

