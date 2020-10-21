package com.elikill58.luckyblocks.good;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.elikill58.luckyblocks.LuckyBlocks;
import com.elikill58.luckyblocks.Rain;

public class RainIngot implements GoodLuckyBlock {
	
	private ArrayList<ItemStack> items = new ArrayList<>();
	
	@Override
	public void run(BlockBreakEvent e) {
		LuckyBlocks.zoneRain(e, Material.AIR);
		items.clear();
		items.add(new ItemStack(Material.GOLDEN_APPLE));
		items.add(new ItemStack(Material.IRON_INGOT));
		items.add(new ItemStack(Material.GOLD_INGOT));
		items.add(new ItemStack(Material.DIAMOND));
		items.add(new ItemStack(Material.IRON_INGOT));
		items.add(new ItemStack(Material.IRON_INGOT));
		items.add(new ItemStack(Material.GOLD_INGOT));
		items.add(new ItemStack(Material.DIAMOND));
		new Rain(e.getBlock().getLocation().add(0, 2, 0), e.getBlock().getWorld(), 33, items).runTaskTimer(LuckyBlocks.INSTANCE, 3, 3);
	}
}
