package com.elikill58.luckyuhc.luckyblocks.good;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class WaterBucket extends GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getBlock().getWorld().dropItemNaturally(e.getPlayer().getLocation(), new ItemStack(Material.WATER_BUCKET));
	}

}
