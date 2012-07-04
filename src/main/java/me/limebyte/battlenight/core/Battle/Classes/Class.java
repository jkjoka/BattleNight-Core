package me.limebyte.battlenight.core.Battle.Classes;

import java.util.List;

import me.limebyte.battlenight.core.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Class {

	private String name;
	private List<ItemStack> items, armor;
	
	public Class(String name, List<ItemStack> items, List<ItemStack> armor) {
		this.name = name;
		this.items = items;
		this.armor = Util.sortArmor(armor);
	}
	
	public String getName() {
		return name;
	}
	
	public List<ItemStack> getItems() {
		return items;
	}

	public List<ItemStack> getArmor() {
		return armor;
	}

	public void equip(Player player) {
		PlayerInventory inv = player.getInventory();
		
		for (ItemStack stack : items) {
			inv.addItem(stack);
		}
		
        inv.setHelmet		(armor.get(1));
        inv.setChestplate	(armor.get(2));
        inv.setLeggings		(armor.get(3));
        inv.setBoots		(armor.get(4));
	}
	
}
