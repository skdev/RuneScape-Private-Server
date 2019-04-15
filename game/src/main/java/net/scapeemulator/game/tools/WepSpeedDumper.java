package net.scapeemulator.game.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.ItemDefinitions;

public class WepSpeedDumper {
	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/wep-speeds3.txt"));
		for(int i = 5292; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				String speed = getSpeed(def.getName());
				writer.write(i + " - " + speed);
				writer.flush();
				writer.newLine();
				System.out.println("ITEM: [" + i + "]" + def.getName() + " - " + speed);
			}
		}
		writer.close();
	}
	
	public static String getSpeed(String name) throws IOException {
		try {
			String itemName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + itemName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				String[] read = line.split(" ");
				if (line.contains("Interval:")) {
					int index = 0;
					for (String s : read) {
						if (s.contains("Interval:")) {
							break;
						}
						index++;
					}
					return read[++index];
				}
			}
			stream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "-1";
	}
}
