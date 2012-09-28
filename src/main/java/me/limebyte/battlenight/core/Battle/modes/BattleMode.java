package me.limebyte.battlenight.core.Battle.modes;

import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public interface BattleMode {
	
	public static final int NUM_TEAMS = 2;
	
	public void onDeath(Player victim, Player killer);
	public void onRespawn();
	public int getNumTeams();
	
}
