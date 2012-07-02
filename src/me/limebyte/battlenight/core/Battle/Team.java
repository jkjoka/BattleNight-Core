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
	
	private final String name;
	private TeamColor teamColor;
	private List<Player> players;
	
    ////////////////////
    //  Constructors  //
    ////////////////////
	
    /**
     * Create a new team by specifying the name and the color for this Team.
     * @param the name and the color for the team
     */
	public Team(String name, TeamColor teamColor) {
		this.name = name;
		this.teamColor = teamColor;
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
		return teamColor.getChatColor();
	}

    /**
     * Gets the HeadBlock for the Team.
     * @return the current head block.
     */
	public ItemStack getHeadBlock() {
	    return teamColor.getHeadBlock();
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
    
    ////////////////////
    //  Enumerators   //
    ////////////////////
    
    public enum TeamColor {
        
        ORANGE      (ChatColor.GOLD, DyeColor.ORANGE),
        AQUA        (ChatColor.AQUA, DyeColor.LIGHT_BLUE),
        YELLOW      (ChatColor.YELLOW, DyeColor.YELLOW),
        LIME        (ChatColor.GREEN, DyeColor.LIME),
        PINK        (ChatColor.LIGHT_PURPLE, DyeColor.PINK),
        SILVER      (ChatColor.GRAY, DyeColor.SILVER),
        CYAN        (ChatColor.DARK_AQUA, DyeColor.CYAN),
        PURPLE      (ChatColor.DARK_PURPLE, DyeColor.PURPLE),
        BLUE        (ChatColor.BLUE, DyeColor.BLUE),
        GREEN       (ChatColor.DARK_GREEN, DyeColor.GREEN),
        RED         (ChatColor.RED, DyeColor.RED);
        
        private final ChatColor chatColor;
        private final DyeColor headColor;
        
        private TeamColor(ChatColor chatColor, DyeColor headColor) {
            this.chatColor = chatColor;
            this.headColor = headColor;
        }
        
        public ChatColor getChatColor() {
            return chatColor;
        }
        
        public ItemStack getHeadBlock() {
            return new ItemStack(Material.WOOL, 1, (short)0, headColor.getData());
        }
        
    }
    
    
}
