package net.scapeemulator.game.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.item.ItemDefinitions;

public class EquipmentSlotDumper {
	
	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-slots.txt"));
		for(int i = 0; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				int slot = getSlot(def.getName());
				writer.write(i + " - " + slot);
				writer.flush();
				writer.newLine();
				System.out.println("ITEM: [" + i + "]" + def.getName() + " - " + slot);
			}
		}
		writer.close();
	}
	
	public static int getSlot(String name) throws IOException {
		try {
			String itemName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + itemName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				int slot = -1;
				
				if (line.contains("Head_slot.png")) {
					
					slot = Equipment.HEAD;
					return slot;
					
				} else if (line.contains("Cape_slot.png")) {
					
					slot = Equipment.CAPE;
					
				} else if (line.contains("Neck_slot.png")) {
					
					slot = Equipment.NECK;
					return slot;
					
				} else if (line.contains("Weapon_slot.png")) {
					
					slot = Equipment.WEAPON;
					return slot;
					
				} else if (line.contains("Torso_slot.png")) {
					
					slot = Equipment.BODY;
					return slot;
					
				} else if (line.contains("Shield_slot.png")) {
					
					slot = Equipment.SHIELD;
					return slot;
					
				} else if (line.contains("Legs_slot.png")) {
					
					slot = Equipment.LEGS;
					return slot;
					
				} else if (line.contains("Gloves_slot.png")) {
					
					slot = Equipment.HANDS;
					return slot;
					
				} else if (line.contains("Boots_slot.png")) {
					
					slot = Equipment.FEET;
					return slot;
					
				} else if (line.contains("Ring_slot.png")) {
					
					slot = Equipment.RING;
					return slot;
					
				} else if (line.contains("Ammo_slot.png")) {
					slot = Equipment.AMMO;
					return slot;
				}
				
			}
			stream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
