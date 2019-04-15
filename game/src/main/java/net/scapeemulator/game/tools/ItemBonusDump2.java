package net.scapeemulator.game.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.ItemDefinitions;

public class ItemBonusDump2 {
	public static void dump() {
		for (int i = 0; i <= 20000; i++) {
			int[] bonuses = new int[14];
			int index = 0;
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				try {
					URL url = new URL("http://2007.runescape.wikia.com/wiki/" + def.getName().replaceAll(" ", "_"));
					URLConnection connection = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-bonuses.txt", true));
					String line;
					
					while ((line = in.readLine()) != null) {
						if (line.contains("<td style=\"text-align: center; width: 35px;\">")) {
							line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("\"\"", "").replace("<td style=\"text-align: center; width: 35px;\">", "");
							bonuses[index] = Integer.parseInt(line);
							index++;
						} else if (line.contains("<td style=\"text-align: center; width: 30px;\">")) {
							line = line.replace("</td>", "").replace("%", "").replace("?", "").replace("%", "").replace("<td style=\"text-align: center; width: 30px;\">", "");
							bonuses[index] = Integer.parseInt(line);
							index++;
						}
					}
					
					in.close();
					String s = i + " ";
					for (int i1 = 0; i1 < bonuses.length; i1++) {
						s+= bonuses[i1] + " ";
					}
					writer.write(i + " " + s);
					System.out.println(def.getName() + "[" + i + "] " + s);
					bonuses[0] = bonuses[1] = bonuses[2] = bonuses[3] = bonuses[4] = bonuses[5] = bonuses[6] = bonuses[7] = bonuses[8] = bonuses[9] = bonuses[10] = bonuses[11] = 0;
					writer.newLine();
					writer.close();
				} catch (Exception e) {
					continue;
				}
			}
		}
	}
}
