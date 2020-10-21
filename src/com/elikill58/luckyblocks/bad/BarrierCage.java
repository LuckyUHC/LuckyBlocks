package com.elikill58.luckyblocks.bad;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

import com.elikill58.luckyblocks.LuckyBlocks;

public class BarrierCage implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		LuckyBlocks.cageBadLuck(e.getPlayer(), Material.BARRIER, Material.BARRIER, Material.WATER);
	}
}
