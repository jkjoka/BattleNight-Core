package me.limebyte.battlenight.core.Battle;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config;

import org.bukkit.Location;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Waypoints {

    Arena arena;
    Config.ConfigFile configFile = Config.ConfigFile.WAYPOINTS;

    // Get Main Class
    public static BattleNight plugin;

    public Waypoints(BattleNight instance, Arena a) {
        plugin = instance;
        arena = a;
    }

    public void setLocation(Waypoint wp, Location loc) {
        String location = plugin.util.locationToString(loc);
        plugin.config.get(configFile).set(arena.getName()+"."+wp.getName(wp), location);
        
        // Save the configuration
        plugin.config.save(configFile);
    }

    public Location getLocation(Waypoint wp) {
        // Reload the configuration
        plugin.config.reload(configFile);
        
        String loc = plugin.config.get(configFile).getString(arena.getName()+"."+wp.getName(wp));
        return plugin.util.locationFromString(loc);
    }

    public boolean isSet(Waypoint wp) {
        return getLocation(wp) != null;
    }

    public enum Waypoint {
        BLUELOUNGE,
        REDLOUNGE,
        BLUESPAWN,
        REDSPAWN,
        EXIT;
        
        public String getName(Waypoint wp) {
            return wp.toString().toLowerCase();
        }
    }
}

