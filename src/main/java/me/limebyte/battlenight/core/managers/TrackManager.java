package me.limebyte.battlenight.core.managers;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Configuration.Config;
import me.limebyte.battlenight.core.Configuration.ConfigurationManager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class TrackManager {

    // Get Main Class
    public static BattleNight plugin;
    public TrackManager(BattleNight instance) {
        plugin = instance;
    }
    
    private static String lang = "en.";
    private static final String prefix = ChatColor.GRAY + "[BattleNight] " + ChatColor.WHITE;

    public static void setLanguage(String lang) {
    	TrackManager.lang = lang + ".";
    }
    
    public enum Track {
	    NO_PERMISSION			("Commands.NoPermission"),
	    PLAYER_ONLY				("Commands.PlayerOnly"),
	    INVALID_COMMAND			("Commands.InvalidCommand"),
	    PLAYER_NOT_FOUND		("Commands.PlayerNotFound");

        private Track(String configPath) {
            this.cp = configPath;
        }
        
        private String cp;

        public String getMessage() {
            return prefix + ChatColor.translateAlternateColorCodes('&', getConfigMessage());
        }
        
        public String getMessage(String... args) {
        	String track = getConfigMessage().replaceAll("<arg([0-9])>", args[Integer.parseInt("$1")]);
        	return prefix + ChatColor.translateAlternateColorCodes('&', track);
        }
        
        private String getConfigMessage() {
        	FileConfiguration config = ConfigurationManager.get(Config.TRACKS);
        	String track;
        	
        	if (config.getString(lang + cp) == null) {
        		if (config.getString("en." + cp) == null) {
        			track = ChatColor.RED + "Track '" + cp + "' not found.  Please tell the author about this issue.";
        		}
        		else {
        			track = config.getString("en." + cp);
        		}
        	}
        	else {
        		track = config.getString(lang + cp);
        	}
        	
        	return track;
        }
    }
}
