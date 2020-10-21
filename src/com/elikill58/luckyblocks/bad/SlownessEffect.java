package com.elikill58.luckyblocks.bad;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessEffect implements BadLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 8, 1));
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 8, 1));
	}
}
