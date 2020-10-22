package com.elikill58.luckyuhc.luckyblocks.good;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.elikill58.luckyblocks.good.GoodLuckyBlock;

public class Compass extends GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent event) {
		event.getBlock().getWorld().dropItemNaturally(event.getPlayer().getLocation(), new ItemStack(Material.COMPASS));
	}

}
