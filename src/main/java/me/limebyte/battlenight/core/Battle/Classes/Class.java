package me.limebyte.battlenight.core.Battle.Classes;

import java.util.List;

import me.limebyte.battlenight.core.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Class {

	private String name;
	private List<ItemStack> items, armour;
	
	public Class(String name, List<ItemStack> items, List<ItemStack> armour) {
		this.name = name;
		this.items = items;
		this.armour = Util.sortArmour(armour);
	}
	
	public String getName() {
		return name;
	}
	
	public List<ItemStack> getItems() {
		return items;
	}

	public List<ItemStack> getArmour() {
		return armour;
	}

	public void equip(Player player) {
		PlayerInventory inv = player.getInventory();
		
		for (ItemStack stack : items) {
			inv.addItem(stack);
		}
		
        inv.setHelmet		(armour.get(0));
        inv.setChestplate	(armour.get(1));
        inv.setLeggings		(armour.get(2));
        inv.setBoots		(armour.get(3));
	}
	
}
