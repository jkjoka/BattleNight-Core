package me.limebyte.battlenight.core.Battle.Modes;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Battle;
import me.limebyte.battlenight.core.Battle.Team;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Mode implements BattleMode,Listener {

	private Team teamA;
	private Team teamB;
	
    // Get Main Class
    public static BattleNight plugin;
    public Mode(BattleNight instance) {
        plugin = instance;
    }
    
	private Battle battle = plugin.getBattle();
	
	public void onEntityDeath(EntityDeathEvent event) {
		
		if (event.getEntityType() != EntityType.PLAYER) return;
	    Player victim = (Player) event.getEntity();
	    
	    if (!battle.containsPlayer(victim)) return;
	    if (!battle.isInProgress()) return;
	    
	    event.getDrops().clear();
	    event.setDroppedExp(0);
	    
	    Player killer = victim.getKiller();
	    
	    if (killer != null) plugin.getPlayerData().addKill(killer);
	    plugin.getPlayerData().addDeath(victim);
	    
		onDeath(victim, killer);
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		onRespawn();
	}
	
	public void onDeath(Player victim, Player killer) {
		
	}
	
	public void onRespawn() {
		
	}
	
	public Team getTeamA() {
		return teamA;
	}
	
	public Team getTeamB() {
		return teamB;
	}
	
}
