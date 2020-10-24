package com.elikill58.luckyuhc.luckyblocks.good;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;

public class SpawnSheep extends GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		Block b = e.getBlock();
		b.getLocation().add(1, 0, 0).getBlock().setType(Material.AIR);
		b.getWorld().spawnEntity(b.getLocation(), EntityType.SHEEP);
	}

}
