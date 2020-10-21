package com.elikill58.luckyblocks.grenade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.zonefun.api.spigot.APIUtils;
import fr.zorahosting.luckyuhc.core.Utils;

public enum GrenadeType {

	REPULSIVE(true, "&rRepulsive", Utils.createItem(Material.NETHER_STAR, ChatColor.RESET + "Repulsive Grenade")),
	BAD_REPULSIVE(false, "&rRepulsive Bad", Utils.createItem(Material.NETHER_STAR, ChatColor.RESET + "Bad Repulsive Grenade"));
	
	private boolean isGood;
	private String name;
	private ItemStack item;
	
	private GrenadeType(boolean isGood, String name, ItemStack item) {
		this.isGood = isGood;
		this.name = APIUtils.applyColorCodes(name);
		this.item = item;
	}
	
	public boolean isGood() {
		return isGood;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public static GrenadeType getRandomGrenade(boolean isGood) {
		List<GrenadeType> types = new ArrayList<>();
		for(GrenadeType grenade : values())
			if(grenade.isGood() == isGood)
				types.add(grenade);
		return types.get(new Random().nextInt(types.size()));
	}
	
	public static Optional<GrenadeType> getGrenadeType(ItemStack item){
		item = item.clone();
		item.setAmount(1);
		for(GrenadeType grenade : values())
			if(grenade.getItem().isSimilar(item))
				return Optional.of(grenade);
		return Optional.empty();
	}
}
