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
	private TeamColour teamColour;
	private List<Player> players;
	
    ////////////////////
    //  Constructors  //
    ////////////////////
	
    /**
     * Create a new team by specifying the name and the color for this Team.
     * @param the name and the color for the team
     */
	public Team(String name, TeamColour teamColour) {
		this.name = name;
		this.teamColour = teamColour;
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
		return teamColour.getChatColor();
	}

    /**
     * Gets the HeadBlock for the Team.
     * @return the current head block.
     */
	public ItemStack getHeadBlock() {
	    return teamColour.getHeadBlock();
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
    
    public enum TeamColour {
        
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
        
        private final ChatColor chatColour;
        private final DyeColor headColour;
        
        private TeamColour(ChatColor chatColour, DyeColor headColour) {
            this.chatColour = chatColour;
            this.headColour = headColour;
        }
        
        public ChatColor getChatColor() {
            return chatColour;
        }
        
        public ItemStack getHeadBlock() {
            return new ItemStack(Material.WOOL, 1, (short)0, headColour.getData());
        }
        
    }
    
    
}
