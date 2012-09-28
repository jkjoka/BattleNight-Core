package me.limebyte.battlenight.core.Battle.modes;

import me.limebyte.battlenight.core.Battle.Team;

import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class TeamDeathMatch implements BattleMode {
	
	private Team teamA;
	private Team teamB;
	
	public TeamDeathMatch(Team team1, Team team2) {
		teamA = team1;
		teamB = team2;
	}
	
	public void onDeath(Player victim, Player killer) {
		// TODO Auto-generated method stub
		
	}

	public void onRespawn() {
		// TODO Auto-generated method stub
		
	}
	
	public Team getTeamA() {
		return teamA;
	}
	
	public Team getTeamB() {
		return teamB;
	}

	@Override
	public int getNumTeams() {
		return 2;
	}

}
