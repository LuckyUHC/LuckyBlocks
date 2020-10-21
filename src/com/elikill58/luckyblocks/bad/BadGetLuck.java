package com.elikill58.luckyblocks.bad;

import org.bukkit.event.block.BlockBreakEvent;

import fr.zonefun.gameapi.GameAPI;

public class BadGetLuck implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		GameAPI.broadcast("luckyblock.break.bad.get_luck", "%name%", e.getPlayer().getName());
	}

}
