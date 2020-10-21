package com.elikill58.luckyblocks.good;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantTable implements GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getBlock().getWorld().dropItemNaturally(e.getPlayer().getLocation(),
				new ItemStack(Material.ENCHANTMENT_TABLE));
	}

}
