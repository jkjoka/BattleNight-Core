package me.limebyte.battlenight.core.Battle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a Team
 * 
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public class Team {
	
	private String name;
	private ChatColor chatColor;
	private DyeColor headBlockColor;
	private List<Player> players;
	
    ////////////////////
    //  Constructors  //
    ////////////////////
	
    /**
     * Create a new team by specifying the name, chat color and the head block color for this Team.
     */
	public Team(String name, ChatColor chatColour, DyeColor headBlockColor) {
		this.name = name;
		this.chatColor = chatColour;
		this.headBlockColor = headBlockColor;
		this.players = new ArrayList<Player>();
	}
	
    ////////////////////
    //    Methods     //
    ////////////////////
	
    /**
     * Adds a Player to the Team.
     * @param the player to add
     */
	public void addPlayer(Player p) {
		players.add(p);
	}
	
    /**
     * Removes a Player from the Team.
     * @param the player to remove
     */
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
    ////////////////////
    //    Getters     //
    ////////////////////
	
    /**
     * Gets the name of the Team.
     * @return the current name.
     */
	public String getName() {
		return name;
	}
	
    /**
     * Gets the ChatColor for the Team.
     * @return the current chat color.
     */
	public ChatColor getChatColor() {
		return chatColor;
	}

    /**
     * Gets the HeadBlock for the Team.
     * @return the current head block.
     */
	public ItemStack getHeadBlock() {
	    return new ItemStack(Material.WOOL, 1, (short)0, headBlockColor.getData());
	}
	
    /**
     * Gets the Players in the Team.
     * @return the players.
     */
	public List<Player> getPlayers() {
		return players;
	}
	
    /**
     * Gets the amount of Players in the Team.
     * @return the number of players.
     */
    public int getPlayerAmount() {
        return players.size();
    }
}
