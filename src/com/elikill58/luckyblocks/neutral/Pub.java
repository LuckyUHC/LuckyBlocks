package com.elikill58.luckyblocks.neutral;

import org.bukkit.event.block.BlockBreakEvent;

import com.elikill58.luckyblocks.good.GoodLuckyBlock;

public class Pub implements GoodLuckyBlock {

	@Override
	public void run(BlockBreakEvent e) {
		e.getPlayer().sendMessage("Vous êtes notre 10.000eme visiteur ! Allez des maintenant sur www.grossearnaque.co.tk/on-te-prend-plein-de-fric-par-seconde/ pour récupérer vos biens !!!!");
	}

}
