package com.elikill58.luckyblocks.bad;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;

public class SpawnSkeleton extends BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		Block b = e.getBlock();
		b.getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);
		b.getWorld().spawnEntity(b.getLocation(), EntityType.SKELETON);
	}

}
