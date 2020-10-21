package com.elikill58.luckyblocks.good;

import org.bukkit.event.block.BlockBreakEvent;

import com.elikill58.luckyblocks.LuckyBlocks;
import com.elikill58.luckyblocks.grenade.GrenadeType;

public class GoodGrenade extends GoodLuckyBlock {
	
	@Override
	public void run(BlockBreakEvent e) {
		if(LuckyBlocks.runGrenade)
			LuckyBlocks.runRandomLuckyBlock(e, 0);
		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), GrenadeType.getRandomGrenade(true).getItem());
	}
	
}
