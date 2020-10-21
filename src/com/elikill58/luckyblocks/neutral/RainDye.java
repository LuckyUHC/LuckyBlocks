package com.elikill58.luckyblocks.neutral;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.elikill58.luckyblocks.LuckyBlocks;
import com.elikill58.luckyblocks.Rain;
import com.elikill58.luckyblocks.good.GoodLuckyBlock;

public class RainDye implements GoodLuckyBlock {
    
	@Override
	public void run(BlockBreakEvent e) {
		LuckyBlocks.zoneRain(e, Material.AIR);
		ArrayList<ItemStack> items = new ArrayList<>();
		for(int i = 0; i != 16; i++)
			items.add(new ItemStack(Material.INK_SACK, 1, (short) i));
		new Rain(e.getBlock().getLocation().add(0, 2, 0), e.getBlock().getWorld(), 32, items).runTaskTimer(LuckyBlocks.INSTANCE, 3, 3);
	}
}
