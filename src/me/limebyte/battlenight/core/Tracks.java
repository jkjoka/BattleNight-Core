package me.limebyte.battlenight.core;

import me.limebyte.battlenight.core.Configuration.Config;

import org.bukkit.ChatColor;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Tracks {

    // Get Main Class
    public static BattleNight plugin;
    public Tracks(BattleNight instance) {
        plugin = instance;
    }
    
    private static final String prefix = ChatColor.GRAY + "[BattleNight] " + ChatColor.WHITE;

    public enum Track {
	    NO_PERMISSION			("Commands.NoPermission"),
	    PLAYER_ONLY				("Commands.PlayerOnly"),
	    PLAYER_NOT_FOUND		("Commands.PlayerNotFound");

        private Track(String configPath) {
            this.cp = configPath;
        }
        private String cp;

        public String getMessage() {
            String track = plugin.getConfigManager().get(Config.ConfigFile.TRACKS).getString(cp);
            return prefix + ChatColor.translateAlternateColorCodes('&', track);
        }
        
        public String getMessage(String... args) {
        	String track = plugin.getConfigManager().get(Config.ConfigFile.TRACKS).getString(cp).replaceAll("<arg([0-9])>", args[Integer.parseInt("$1")]);
        	return prefix + ChatColor.translateAlternateColorCodes('&', track);
        }
    }
}
