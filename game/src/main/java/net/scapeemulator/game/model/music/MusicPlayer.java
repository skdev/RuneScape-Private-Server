package net.scapeemulator.game.model.music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.region.Region;
import net.scapeemulator.game.msg.InterfaceTextMessage;
import net.scapeemulator.game.msg.MusicMessage;

public class MusicPlayer {
	private static final Logger logger = LoggerFactory.getLogger(MusicPlayer.class);
	private static final List<Music> songs = new ArrayList<>();

	public static void playMusic(Player player) {
		boolean playing = false;
		int id = Region.getRegionId(player.getPosition());
		for (Music music : songs) {
			if (music.getRegionId() == id) {
				if (music.isLocked()) {
					music.unlock();
					player.sendMessage("<col=C70000>You have unlocked the song " + music.getName() + ".</col>");
				}
				player.send(new MusicMessage(music.getId(), 500));
				player.send(new InterfaceTextMessage(187, 14, music.getName()));
				playing = true;
				break;
			}
		}
		
		if (!playing) {
			player.send(new MusicMessage(3, 500));
			player.send(new InterfaceTextMessage(187, 14, "Unknown Land"));
		}
	}
	
	public static void load() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/music.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null) {
			if (read.startsWith("//")) {
				continue;
			}
			String[] parsed = read.split("-");
			int regionId = Integer.parseInt(parsed[0].trim());
			int musicId = Integer.parseInt(parsed[1].trim());
			String name = parsed[2].trim();
			songs.add(new Music(musicId, regionId, name));
			count++;
		}
		reader.close();
		logger.info("Loaded " + count + " music ids.");
	}
	
}
