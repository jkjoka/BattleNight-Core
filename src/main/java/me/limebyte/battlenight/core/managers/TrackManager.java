package me.limebyte.battlenight.core.managers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.limebyte.battlenight.core.configuration.Config;
import me.limebyte.battlenight.core.configuration.ConfigurationManager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class TrackManager {
    
    private static String lang = "en.";
    private static final String prefix = ChatColor.GRAY + "[BattleNight] " + ChatColor.WHITE;

    public static void setLanguage(String lang) {
    	TrackManager.lang = lang + ".";
    }
    
    public enum Track {
	    NO_PERMISSION			("Commands.NoPermission"),
	    PLAYER_ONLY				("Commands.PlayerOnly"),
	    INVALID_COMMAND			("Commands.InvalidCommand"),
	    PLAYER_NOT_FOUND		("Commands.PlayerNotFound"),
	    INVALID_WAYPOINT		("Commands.InvalidWaypoint"),
	    GLOBAL_WAYPOINT_SET		("Commands.GlobalWaypointSet"),
	    ARENA_WAYPOINT_SET		("Commands.ArenaWaypointSet");

        private Track(String configPath) {
            this.cp = configPath;
        }
        
        private String cp;

        public String getMessage() {
            return prefix + ChatColor.translateAlternateColorCodes('&', getConfigMessage());
        }
        
        public String getMessage(String... args) {
        	String track = parseInArgs(getConfigMessage(), args);
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
        
        private String parseInArgs(String message, String... args) {
        	Pattern pattern = Pattern.compile("<arg([0-9])>");
        	Matcher matcher = pattern.matcher(message);
        	
        	StringBuffer sb = new StringBuffer(message.length());
        	while (matcher.find()) {
        		String text = args[Integer.parseInt(matcher.group(1))];
        	    matcher.appendReplacement(sb, Matcher.quoteReplacement(text));
        	}
        	matcher.appendTail(sb);
        	return sb.toString();
        }
    }
}
