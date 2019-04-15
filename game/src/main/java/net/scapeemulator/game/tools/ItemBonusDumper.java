package net.scapeemulator.game.tools;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.ItemDefinitions;

/**
 *
 * @since 1/25/16.
 */
public class ItemBonusDumper {

	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-bonuses.txt"));
		for (int i = 0; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				String tradable = fetchItem(def.getName());
				writer.write(i + " - " + tradable);
				writer.flush();
				writer.newLine();
				System.out.println("ITEM: [" + i + "]" + def.getName() + " - " + tradable);
			}
		}
		writer.close();
	}

	static String extractBonuses(String text) {
		if (!text.contains("Bonuses")) {
			return "0 0 0 0 0 0 0 0 0 0 0 0 0";
		}
		try {
			int bonusStart = text.indexOf("Bonuses");
			int bonusEnd = text.indexOf("</table>", bonusStart);
	
			// skip first row
			int idx = text.indexOf("</tr>", bonusStart);
			StringBuilder result = new StringBuilder();
			while (idx < bonusEnd) {
				idx = text.indexOf("<td", idx);
				idx = text.indexOf(">", idx) + 1;
	
				if (idx > bonusEnd)
					break;
	
				int endTxt = text.indexOf("</td", idx);
				if (text.length() > 30)
					return "0 0 0 0 0 0 0 0 0 0 0 0 0";
				result.append(text.substring(idx, endTxt).trim());
				result.append(" ");
			}
			return result.toString();
		} catch (Exception e) {
			return "0 0 0 0 0 0 0 0 0 0 0 0 0";
		}

	}

	static String fetchItem(String item) throws IOException {
		URL url = new URL("http://2007.runescape.wikia.com/wiki/" + item);
		URLConnection connection = url.openConnection();

		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		try (InputStream is = connection.getInputStream()) {
			byte[] b = new byte[1024];
			int c;
			while ((c = is.read(b)) > -1) {
				buf.write(b, 0, c);
			}
		} catch (Exception e) {
			return "0 0 0 0 0 0 0 0 0 0 0 0 0";
		}

		return extractBonuses(new String(buf.toByteArray()));
	}

}