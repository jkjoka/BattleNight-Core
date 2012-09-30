package me.limebyte.battlenight.core.Listeners;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.TagAPI.PlayerReceiveNameTagEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NameTagListener implements Listener {

	// Get Main Class
	public static BattleNight plugin;
	public NameTagListener(BattleNight instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onNameTag(PlayerReceiveNameTagEvent event) {
		String name = event.getNamedPlayer().getName();
		
		if (plugin.BattleUsersTeam.containsKey(name)) {
			String team = plugin.BattleUsersTeam.get(name);
			if (team == "red") {
				event.setTag(ChatColor.RED + name);
				BattleNight.log.info("Good");
			} else if (team =="blue") {
				event.setTag(ChatColor.BLUE + name);
				BattleNight.log.info("Good");
			} else {
				BattleNight.log.info("Bad");
				return;
			}
		}
		
		BattleNight.log.info("Badish");
		event.setTag(name);
	}
	
}
