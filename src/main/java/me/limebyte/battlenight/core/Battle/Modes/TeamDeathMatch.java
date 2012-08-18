package me.limebyte.battlenight.core.Battle.Modes;

import me.limebyte.battlenight.core.Battle.Team;

import org.bukkit.entity.Player;

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
