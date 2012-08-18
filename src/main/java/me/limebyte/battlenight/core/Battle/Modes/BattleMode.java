package me.limebyte.battlenight.core.Battle.Modes;

import org.bukkit.entity.Player;

public interface BattleMode {
	
	public static final int NUM_TEAMS = 2;
	
	public void onDeath(Player victim, Player killer);
	public void onRespawn();
	public int getNumTeams();
	
}
