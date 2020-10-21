package com.elikill58.luckyblocks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.elikill58.api.UniversalUtils;
import com.elikill58.luckyblocks.bad.BadLuckyBlock;
import com.elikill58.luckyblocks.good.GoodLuckyBlock;
import com.elikill58.luckyblocks.grenade.GrenadeManager;
import com.elikill58.luckyblocks.neutral.NeutralLuckyBlock;
import com.google.common.io.ByteStreams;

public class LuckyBlocks extends JavaPlugin {

	public static LuckyBlocks INSTANCE;
	
	public static boolean runGrenade = true;
	private static List<LuckyBlockAbstract> goodLuckyBlocks = new ArrayList<>();
	private static List<LuckyBlockAbstract> neutralLuckyBlocks = new ArrayList<>();
	private static List<LuckyBlockAbstract> badLuckyBlocks = new ArrayList<>();
	
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

		manageBlockType(GoodLuckyBlock.class, "good", goodLuckyBlocks);
		manageBlockType(BadLuckyBlock.class, "bad", badLuckyBlocks);
		manageBlockType(NeutralLuckyBlock.class, "neutral", neutralLuckyBlocks);
		
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
	
	public static void manageBlockType(Class<?> clazz, String name, List<LuckyBlockAbstract> list) {
		try {
			String dir = clazz.getProtectionDomain().getCodeSource().getLocation().getFile().replaceAll("%20", " ");
			if (dir.endsWith(".class"))
				dir = dir.substring(0, dir.lastIndexOf('!'));

			if (dir.startsWith("file:/"))
				dir = dir.substring(UniversalUtils.getOs() == UniversalUtils.OS.LINUX ? 5 : 6);

			for (Object classDir : UniversalUtils.getClasseNamesInPackage(dir, "com.elikill58.luckyblocks." + name)) {
				try {
					LuckyBlockAbstract cheat = (LuckyBlockAbstract) Class.forName(classDir.toString().replaceAll(".class", "")).newInstance();
					list.add(cheat);
				} catch (Exception temp) {
					// on ignore
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
