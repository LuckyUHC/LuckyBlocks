package com.elikill58.luckyblocks.bad;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

import com.elikill58.luckyblocks.LuckyBlocks;

public class ObsidianCage implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		LuckyBlocks.cageBadLuck(e.getPlayer(), Material.OBSIDIAN, Material.GLASS, Material.WATER);
	}

}
