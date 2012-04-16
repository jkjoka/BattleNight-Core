package me.limebyte.battlenight.core.Battle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Team {
	
	private String name;
	private ChatColor chatColour;
	private List<Player> players;
	
	public Team(String name, ChatColor chatColour) {
		this.name = name;
		this.chatColour = chatColour;
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public String getName() {
		return name;
	}
	
	public ChatColor getChatColor() {
		return chatColour;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
}
