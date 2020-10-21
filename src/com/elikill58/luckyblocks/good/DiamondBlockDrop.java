package com.elikill58.luckyblocks.good;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DiamondBlockDrop implements GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().add(0, 2, 0),
				new ItemStack(Material.DIAMOND_BLOCK, new Random().nextInt(3)));
	}

}
