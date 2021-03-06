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

public class ItemPriceDumper {
	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-prices.txt"));
		for(int i = 0; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				String value = getValue(def.getName());
				writer.write(i + " - " + value);
				writer.flush();
				writer.newLine();
				System.out.println("ITEM: " + i + " - " + value);
			}
		}
		writer.close();
	}
	
	public static String getValue(String name) throws IOException {
		try {
			String npcName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + npcName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				if (line.contains("Value</a>")) {
					String nextLine = stream.readLine();
					String examine = nextLine.replace("</th><td>", "").replace("*", "").replace("#160;", "").replace("&lt;", "").replace("&gt;", "").replace("<br />", "");
					if (examine.endsWith("</small>")) {
						String toRemove = examine.substring(examine.indexOf("<small>"), examine.indexOf("</small>")+8);
						return examine.replace(toRemove, "");
					}
					return examine;
				}
			}
			stream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "0 coins";
	}
}
