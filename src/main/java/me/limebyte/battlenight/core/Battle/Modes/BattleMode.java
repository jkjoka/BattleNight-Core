package me.limebyte.battlenight.core.Battle.Modes;

import org.bukkit.entity.Player;

public interface BattleMode {

	public void onDeath(Player victim, Player killer);
	public void onRespawn();
	
}
