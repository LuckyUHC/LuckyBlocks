package com.elikill58.luckyblocks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

import com.elikill58.luckyblocks.bad.BadLuckyBlock;
import com.elikill58.luckyblocks.good.GoodLuckyBlock;
import com.elikill58.luckyblocks.grenade.GrenadeManager;
import com.elikill58.luckyblocks.neutral.NeutralLuckyBlock;
import com.google.common.io.ByteStreams;

public class LuckyBlocks extends JavaPlugin {

	public static LuckyBlocks INSTANCE;
	
	public static boolean runGrenade = true;
	private static List<GoodLuckyBlock> goodLuckyBlocks = new ArrayList<>();
	private static List<NeutralLuckyBlock> neutralLuckyBlocks = new ArrayList<>();
	private static List<BadLuckyBlock> badLuckyBlocks = new ArrayList<>();
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		getDataFolder().mkdir();
		getDataFolder().mkdirs();
		try (InputStream in = getResource("little_house.schematic");
				OutputStream out = new FileOutputStream(
						new File(getDataFolder().getPath(), "little_house.schematic"))) {
			ByteStreams.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Reflections reflections = new Reflections(ClasspathHelper.forClassLoader(getClass().getClassLoader()),
				new SubTypesScanner());

		Set<Class<? extends GoodLuckyBlock>> goodClasses = reflections.getSubTypesOf(GoodLuckyBlock.class);
		Set<Class<? extends NeutralLuckyBlock>> neutralClasses = reflections.getSubTypesOf(NeutralLuckyBlock.class);
		Set<Class<? extends BadLuckyBlock>> badClasses = reflections.getSubTypesOf(BadLuckyBlock.class);

		goodClasses.forEach(clazz -> {
			try {
				goodLuckyBlocks.add(clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		neutralClasses.forEach(clazz -> {
			try {
				neutralLuckyBlocks.add(clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		badClasses.forEach(clazz -> {
			try {
				badLuckyBlocks.add(clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		getServer().getPluginManager().registerEvents(new GrenadeManager(), this);
		new CompassTimer().runTaskTimer(this, 40, 10);
	}
	
	public static boolean runRandomLuckyBlockIfIsGood(BlockBreakEvent e, int luck) {
		if (new Random().nextInt(100) <= luck) {
			goodLuckyBlocks.get(new Random().nextInt(goodLuckyBlocks.size())).run(e);
			return true;
		} else
			return false;
	}

	public static void runRandomLuckyBlock(BlockBreakEvent e, int luck) {
		int random = new Random().nextInt(100);
		if((luck + 1) < random && random > (luck -1)) {
			goodLuckyBlocks.get(new Random().nextInt(goodLuckyBlocks.size())).run(e);
		} else if (random <= luck) {
			neutralLuckyBlocks.get(new Random().nextInt(neutralLuckyBlocks.size())).run(e);
		} else
			badLuckyBlocks.get(new Random().nextInt(badLuckyBlocks.size())).run(e);
	}

	public static void cageBadLuck(Player p, Material prison, Material vision, Material tueur) {

		p.getLocation().add(0, 2, 0).getBlock().setType(tueur);
		p.getLocation().add(1, 0, 0).getBlock().setType(prison);
		p.getLocation().add(1, 2, 0).getBlock().setType(prison);
		p.getLocation().add(1, 1, 0).getBlock().setType(vision);
		p.getLocation().add(-1, 0, 0).getBlock().setType(prison);
		p.getLocation().add(-1, 2, 0).getBlock().setType(prison);
		p.getLocation().add(-1, 1, 0).getBlock().setType(vision);
		p.getLocation().add(0, 0, 1).getBlock().setType(prison);
		p.getLocation().add(0, 2, 1).getBlock().setType(prison);
		p.getLocation().add(0, 1, 1).getBlock().setType(vision);
		p.getLocation().add(0, 0, -1).getBlock().setType(prison);
		p.getLocation().add(0, 2, -1).getBlock().setType(prison);
		p.getLocation().add(0, 1, -1).getBlock().setType(vision);
		p.getLocation().add(0, -1, 0).getBlock().setType(prison);

	}

	public static void zoneRain(BlockBreakEvent e, Material m) {
		Location loc = e.getBlock().getLocation();

		loc.clone().add(0, 1, 0).getBlock().setType(m);
		loc.clone().add(0, 2, 0).getBlock().setType(m);
		loc.clone().add(1, 1, 0).getBlock().setType(m);
		loc.clone().add(1, 1, 1).getBlock().setType(m);
		loc.clone().add(1, 1, -1).getBlock().setType(m);
		loc.clone().add(-1, 1, 0).getBlock().setType(m);
		loc.clone().add(-1, 1, 1).getBlock().setType(m);
		loc.clone().add(-1, 1, -1).getBlock().setType(m);
		loc.clone().add(0, 1, 1).getBlock().setType(m);
		loc.clone().add(0, 1, -1).getBlock().setType(m);
		loc.clone().add(1, 2, 0).getBlock().setType(m);
		loc.clone().add(1, 2, 1).getBlock().setType(m);
		loc.clone().add(1, 2, -1).getBlock().setType(m);
		loc.clone().add(-1, 2, 0).getBlock().setType(m);
		loc.clone().add(-1, 2, 1).getBlock().setType(m);
		loc.clone().add(-1, 2, -1).getBlock().setType(m);
		loc.clone().add(0, 2, 1).getBlock().setType(m);
		loc.clone().add(0, 2, -1).getBlock().setType(m);

	}
}
