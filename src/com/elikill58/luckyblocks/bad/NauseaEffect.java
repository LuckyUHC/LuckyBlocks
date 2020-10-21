package com.elikill58.luckyblocks.bad;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NauseaEffect implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10, 1));
	}
}
