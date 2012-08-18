package me.limebyte.battlenight.core.Battle.Modes;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.Battle.Battle;
import me.limebyte.battlenight.core.managers.PlayerManager;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Mode implements BattleMode,Listener {
	
    // Get Main Class
    public static BattleNight plugin;
    public Mode(BattleNight instance) {
        plugin = instance;
    }
    
    private static final int DEFAULT_NUM_TEAMS = 2;
    
	private Battle battle = BattleNight.getBattle();
	
	public void onEntityDeath(EntityDeathEvent event) {
		
		if (event.getEntityType() != EntityType.PLAYER) return;
	    Player victim = (Player) event.getEntity();
	    
	    if (!battle.containsPlayer(victim)) return;
	    if (!battle.isInProgress()) return;
	    
	    event.getDrops().clear();
	    event.setDroppedExp(0);
	    
	    Player killer = victim.getKiller();
	    
	    if (killer != null) PlayerManager.addKill(killer);
	    PlayerManager.addDeath(victim);
	    
		onDeath(victim, killer);
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		onRespawn();
	}
	
	public void onDeath(Player victim, Player killer) {
		
	}
	
	public void onRespawn() {
		
	}
	
	public int getNumTeams() {
		return DEFAULT_NUM_TEAMS;
	}
	
}
