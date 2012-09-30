/* Copyright 2012 Matt Baxter
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package me.limebyte.battlenight.core.TagAPI;

import java.util.HashMap;
import java.util.Set;

import me.limebyte.battlenight.core.BattleNight;
import me.limebyte.battlenight.core.TagAPI.RefreshPair;
import net.minecraft.server.*;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TagAPI {

   private static TagAPI instance;

   /**
    * Flicker the player for anyone who can see him.
    * 
    * @param player
    */
   public static void refreshPlayer(Player player) {
       if (player == null) {
           throw new TagAPIException("Can't submit null player!");
       }
       if (!player.isOnline()) {
           throw new TagAPIException("Can't submit offline player!");
       }
       final ShowBomb bomb = new ShowBomb();
       for (final Player otherGuy : player.getWorld().getPlayers()) {
           if ((!otherGuy.equals(player)) && otherGuy.canSee(player)) {
               otherGuy.hidePlayer(player);
               bomb.queue(new RefreshPair(otherGuy, player));
           }
       }
       Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BattleNight.instance, bomb, 2);
   }

   /**
    * Flicker the player for a player who can see him.
    * 
    * @param player
    * @param forWhom
    */
   public static void refreshPlayer(Player player, Player forWhom) {
       if (player == null) {
           throw new TagAPIException("Can't submit null player!");
       }
       if (forWhom == null) {
           throw new TagAPIException("Can't submit null forWhom!");
       }
       if (player.equals(forWhom)) {
           throw new TagAPIException("Can't submit player on self!");
       }
       final ShowBomb bomb = new ShowBomb();
       if (forWhom.canSee(player) && player.getWorld().equals(forWhom.getWorld())) {
           forWhom.hidePlayer(player);
           bomb.queue(new RefreshPair(forWhom, player));
       }
       Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BattleNight.instance, bomb, 2);
   }

   /**
    * Flicker the player for anyone in a set of players who can see him.
    * 
    * @param player
    * @param forWhom
    */
   public static void refreshPlayer(Player player, Set<Player> forWhom) {
       if (player == null) {
           throw new TagAPIException("Can't submit null player!");
       }
       if ((forWhom == null) || forWhom.isEmpty()) {
           throw new TagAPIException("Can't submit empty forWhom!");
       }
       for (final Player whom : forWhom) {
           if ((whom != null) && (!player.equals(whom))) {
               TagAPI.refreshPlayer(player, whom);
           }
       }
   }

   public HashMap<Integer, EntityPlayer> entityIDMap;
   private PacketHandler handler;

   public void disable() {
       this.handler.shutdown();
   }

   public void enable() {
       instance = this;
       entityIDMap = new HashMap<Integer, EntityPlayer>();
       handler = new PacketHandler(this);
       Bukkit.getPluginManager().registerEvents(new EventListener(this), BattleNight.instance);
       for (final Player player : Bukkit.getOnlinePlayers()) {
           this.in(player);
       }
   }

   public void packet(Packet20NamedEntitySpawn packet, Player destination) {
       if (TagAPI.instance == null) {
           throw new TagAPIException("TagAPI not loaded");
       }
       TagAPI.instance.handlePacket(packet, destination);
   }

   private void handlePacket(Packet20NamedEntitySpawn packet, Player destination) {
       final EntityPlayer entity = this.entityIDMap.get(packet.a);
       if (entity == null) return;
       final Player named = entity.getBukkitEntity();
       if (named == null) return;
       if (destination == null) return;
       final PlayerReceiveNameTagEvent event = new PlayerReceiveNameTagEvent(destination, named);
       Bukkit.getPluginManager().callEvent(event);
       if (event.isModified()) {
           String name = event.getTag();
           if (name.length() > 16) {
               name = name.substring(0, 16);
           }
           packet.b = name;
       }
   }

   public void in(Player player) {
       this.entityIDMap.put(player.getEntityId(), ((CraftPlayer) player).getHandle());
   }

}
