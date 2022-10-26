package it.mathanalisys.vanilla.utils;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class ItemBuilder {

	private ItemStack itemStack;
	private ItemMeta itemMeta;

	public static ItemBuilder copyOf(ItemBuilder builder) {
		return new ItemBuilder(builder.get());
	}

	public static ItemBuilder copyOf(ItemStack item) {
		return new ItemBuilder(item);
	}

	public ItemBuilder(Material material, int amount, short damage) {
		this.itemStack = new ItemStack(material, amount, damage);
		this.itemMeta = itemStack.getItemMeta();
	}

	public ItemBuilder(Material material) {
		this(material, 1, (short) 0);
	}

	public ItemBuilder(ItemStack itemStack) {
		this.itemStack = itemStack;
		this.itemMeta = itemStack.getItemMeta();
	}

	public ItemBuilder setAmount(int amount) {
		this.itemStack.setAmount(Math.min(amount, 64));
		return this;
	}

	public ItemBuilder setFlags(ItemFlag... flags) {
		for (ItemFlag flag : flags) {
			itemMeta.addItemFlags(flags);
		}
		return this;
	}

	public ItemBuilder setName(String name) {
		itemMeta.setDisplayName(CC.translate(name));
		return this;
	}

	public ItemBuilder addLore(List<String> lore) {
		List<String> newLore = itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore();
		newLore.addAll(CC.translateStrings(lore));
		itemMeta.setLore(newLore);
		return this;
	}

	public ItemBuilder addLore(String... lore) {
		return addLore(Arrays.asList(lore));
	}

	public ItemBuilder setLore(List<String> lore) {
		List<String> toSet = new ArrayList<>();
		lore.forEach(string -> toSet.add(CC.translate(string)));
		itemMeta.setLore(toSet);
		return this;
	}

	public ItemBuilder setDurability(int durability) {
		this.itemStack.setDurability((short) durability);
		return this;
	}

	public ItemBuilder setData(int data) {
		this.itemStack.setData(new MaterialData(this.itemStack.getType(), (byte) data));
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		this.itemStack.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment) {
		this.itemStack.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder setType(Material material) {
		this.itemStack.setType(material);
		return this;
	}

	public ItemBuilder clearLore() {
		itemMeta.setLore(new ArrayList<>());
		return this;
	}

	public ItemBuilder clearEnchantments() {
		this.itemStack.getEnchantments().keySet().forEach(e -> this.itemStack.removeEnchantment(e));
		return this;
	}

	public ItemBuilder setColor(org.bukkit.Color color) {
		if(this.itemStack.getType() == Material.LEATHER_BOOTS
				|| this.itemStack.getType() == Material.LEATHER_CHESTPLATE
				|| this.itemStack.getType() == Material.LEATHER_HELMET
				|| this.itemStack.getType() == Material.LEATHER_LEGGINGS) {
			LeatherArmorMeta meta = (LeatherArmorMeta) this.itemStack.getItemMeta();
			meta.setColor(color);
			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor.");
		}
	}

	public ItemBuilder setOwner(String owner) {
		SkullMeta meta = (SkullMeta) itemMeta;
		meta.setOwner(owner);
		return this;
	}

	public ItemStack get() {
		this.itemStack.setItemMeta(itemMeta);
		return this.itemStack;
	}

	public static void rename(ItemStack stack, String name) {
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(CC.translate(name));
		stack.setItemMeta(meta);
	}

	public ItemBuilder setUnbreakable(boolean var){
		ItemMeta meta = itemStack.getItemMeta();
		meta.clone().setUnbreakable(var);
		itemStack.setItemMeta(meta);
		return this;
	}

	public static ItemStack reloreItem(ItemStack stack, String... lore) {
		return reloreItem(ReloreType.OVERWRITE, stack, lore);
	}

	public static ItemStack reloreItem(ReloreType type, ItemStack stack, String... lores) {
		ItemMeta meta = stack.getItemMeta();

		List<String> lore = meta.getLore();
		if(lore == null) {
			lore = new LinkedList<>();
		}

		switch (type) {
			case APPEND -> {
				lore.addAll(Arrays.asList(lores));
				meta.setLore(CC.translateStrings(lore));
			}
			case PREPEND -> {
				List<String> nLore = new LinkedList<>(Arrays.asList(lores));
				nLore.addAll(CC.translateStrings(lore));
				meta.setLore(CC.translateStrings(nLore));
			}
			case OVERWRITE -> meta.setLore(Arrays.asList(lores));
		}

		stack.setItemMeta(meta);
		return stack;
	}

	public enum ReloreType {
		OVERWRITE,
		PREPEND,
		APPEND
	}

	public static ItemStack createItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(CC.translate(name));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, String name, int amount) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(CC.translate(name));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, String name, int amount, short damage) {
		ItemStack item = new ItemStack(material, amount, damage);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(CC.translate(name));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getBackItem() {
		return new ItemBuilder(Material.ARROW).setName(ChatColor.RED + "Back").get();
	}
}