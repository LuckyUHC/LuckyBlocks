package com.elikill58.luckyblocks.bad;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;

public class Tnt implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.PRIMED_TNT);
		e.getPlayer().sendMessage(ChatColor.RED + "Attention !");
	}

}
