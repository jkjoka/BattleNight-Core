package me.limebyte.battlenight.core.Battle.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author LimeByte.
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported
 * http://creativecommons.org/licenses/by-nc-nd/3.0/
 */
public enum ArmourType {
	HELMET		(Material.CHAINMAIL_HELMET,
				 Material.IRON_HELMET,
				 Material.GOLD_HELMET,
				 Material.DIAMOND_HELMET),
	CHESTPLATE  (Material.CHAINMAIL_CHESTPLATE,
				 Material.IRON_CHESTPLATE,
				 Material.GOLD_CHESTPLATE,
				 Material.DIAMOND_CHESTPLATE),
	LEGGINGS	(Material.CHAINMAIL_LEGGINGS,
				 Material.IRON_LEGGINGS,
				 Material.GOLD_LEGGINGS,
				 Material.DIAMOND_LEGGINGS),
	BOOTS		(Material.CHAINMAIL_BOOTS,
				 Material.IRON_BOOTS,
				 Material.GOLD_BOOTS,
				 Material.DIAMOND_BOOTS);
	
	private final Material[] materials;
	
	ArmourType(Material... materials) {
		this.materials = materials;
	}
	
	public final boolean contains(ItemStack stack) {
		for (Material material : materials) {
			if (stack.getType().equals(material)) return true;
		}
		return false;
	}
}
