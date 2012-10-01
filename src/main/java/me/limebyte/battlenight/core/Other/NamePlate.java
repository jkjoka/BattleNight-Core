package me.limebyte.battlenight.core.Other;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet20NamedEntitySpawn;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Utility class to change Player's NamePlates.
 * @author LimeByte
 */
public final class NamePlate {
    //TODO Reset NamePlate when a Player joins the server.

    ///////////////////////
    //   Constructors    //
    ///////////////////////
	
    /**
     * Not meant to be constructed.
     */
    private NamePlate() {
        
    }
    
    
    ///////////////////////
    //  Public Methods   //
    ///////////////////////
    
    /**
     * Sets the colour for the Player's NamePlate. 
     * 
     * @param player The Player you want to change the NamePlate of
     * @param colour The ChatColor you want their name to be
     */
    public static void setColour(Player player, ChatColor colour) {
        set(player, colour.toString() + player.getName());
    }
    
    /**
     * Sets the text on the Player's NamePlate. 
     * 
     * @param player The Player you want to change the NamePlate of
     * @param text The text you want displayed on their NamePlate
     */
    public static void set(Player player, String text) {
        // Save the Player's real name
        String realName = player.getName();
 
        EntityPlayer entity = ((CraftPlayer) player).getHandle();
        entity.name = text;
        refreshPlayer(player, entity);
        
        // Restore the Player's real name
        entity.name = realName;
    }
    
    /**
     * Resets the text on the Player's NamePlate. 
     * 
     * @param player The Player you want to reset the NamePlate of
     */
    public static void reset(Player player) {
        EntityPlayer entity = ((CraftPlayer) player).getHandle();
        refreshPlayer(player, entity); 
    }
    
    
    ///////////////////////
    //  Private Methods  //
    ///////////////////////
    
    /**
     * Refreshes the NamePlate for all other Player currently on the server.
     * 
     * @param player The Bukkit Player having its NamePlate changed
     * @param entity The Minecraft EntityPlayer having its NamePlate changed
     */
    private static void refreshPlayer(Player player, EntityPlayer entity) {
        for (Player playerinworld : Bukkit.getOnlinePlayers()) {
            if (playerinworld != player) {
                ((CraftPlayer) playerinworld).getHandle().netServerHandler.sendPacket(new Packet20NamedEntitySpawn(entity));
            }
        }
    }
}