package net.scapeemulator.game.model.item;

import net.scapeemulator.cache.Archive;
import net.scapeemulator.cache.Cache;
import net.scapeemulator.cache.Container;
import net.scapeemulator.cache.ReferenceTable;
import net.scapeemulator.cache.def.ItemDefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class ItemDefinitions {

	private static final Logger logger = LoggerFactory.getLogger(ItemDefinitions.class);
	private static ItemDefinition[] definitions;

	public static void init(Cache cache) throws IOException {
		int count = 0;

		Container tableContainer = Container.decode(cache.getStore().read(255, 19));
		ReferenceTable table = ReferenceTable.decode(tableContainer.getData());

		int files = table.capacity();
		definitions = new ItemDefinition[files * 256];

		for (int file = 0; file < files; file++) {
			ReferenceTable.Entry entry = table.getEntry(file);
			if (entry == null)
				continue;

			Archive archive = Archive.decode(cache.read(19, file).getData(), entry.size());
			int nonSparseMember = 0;
			for (int member = 0; member < entry.capacity(); member++) {
				ReferenceTable.ChildEntry childEntry = entry.getEntry(member);
				if (childEntry == null)
					continue;

				int id = file * 256 + member;
				ItemDefinition definition = ItemDefinition.decode(archive.getEntry(nonSparseMember++));
				definitions[id] = definition;
				count++;
			}
		}

		logger.info("Loaded " + count + " item definitions.");
		
		parseItemExamination();
		parseItemTradeable();
		parseItemMembers();
		parseItemWeight();
		parseAlchValues();
		parseItemSlots();
		parseItemPrices();
		parseWeaponSpeeds();
		parseItemBonuses();
	}
	
	private static void parseItemBonuses() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-bonuses.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split(" ");
			int itemId = -1;
			try {
				itemId = Integer.parseInt(parsed[0].trim());
			} catch (Exception e) {
				/* Skip, was an error when dumping */
				continue;
			}
			try {
				
				int bonuses[] = new int[14];
				
				for (int i = 1; i < bonuses.length; i++) {
					try {
						bonuses[i-1] = Integer.parseInt(parsed[i]);
					} catch (Exception e) {
						bonuses[i-1] = 0;
					}
				}
				
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setItemBonuses(bonuses);
						count++;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				/* Ignore */
			}
		}
		reader.close();
		logger.info("Loaded " + count + " weapon bonuses.");
	}
	
	private static void parseWeaponSpeeds() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/wep-speeds.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = -1;
			try {
				itemId = Integer.parseInt(parsed[0].trim());
			} catch (Exception e) {
				/* Skip, was an error when dumping */
				continue;
			}
			try {
				String valueRead = parsed[1].trim();
				double speed = -1;
				try {
					speed = Double.parseDouble(valueRead);
				} catch (Exception e) {
					/* Ignore */
				}
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setSpeed(speed);
					}
				}
				if(speed != -1)
					count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " weapon speeds");
	}
	
	private static void parseItemSlots() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-slots.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = 0;
			try {
				itemId = Integer.parseInt(parsed[0].trim());
			} catch (NumberFormatException e) {
				/* Error when dump was created, so we can ignore this line */
				continue;
			}
			try {
				int slot = -1;
				try {
					slot = Integer.parseInt(parsed[1].trim());
				} catch (NumberFormatException e) {
					/* Ignore */
				}
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setSlot(slot);
					}
				}
				if (slot != -1)
					count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item equipment slot data.");
	}
	
	private static void parseAlchValues() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-alch-values.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = 0;
			try {
				itemId = Integer.parseInt(parsed[0].trim());
			} catch (NumberFormatException e) {
				/* Error when dump was created, so we can ignore this line */
				continue;
			}
			try {
				int lowAlch = 0;
				int highAlch = 0;
				try {
					lowAlch = Integer.parseInt(parsed[1].trim().replaceAll("coins", ""));
				} catch (NumberFormatException e) {
					/* Ignore */
				}
				try {
					highAlch = Integer.parseInt(parsed[2].trim().replaceAll("coins", ""));
				} catch (NumberFormatException e) {
					/* Ignore */
				}
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setLowAlchValue(lowAlch);
						definitions[itemId].setHighAlchValue(highAlch);
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " alch values.");
	}
	
	private static void parseItemPrices() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-prices.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = -1;
			try {
				itemId = Integer.parseInt(parsed[0].trim());
			} catch (Exception e) {
				/* Skip, was an error when dumping */
				continue;
			}
			try {
				String valueRead = parsed[1].trim();
				int value = 0;
				try {
					value = Integer.parseInt(valueRead);
				} catch (Exception e) {
					/* Ignore */
				}
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setValue(value);
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item prices.");
	}
	
	private static void parseItemExamination() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-examine.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int objectId = Integer.parseInt(parsed[0].trim());
			try {
				String examine = parsed[1].trim();
				if (objectId < count()) {
					if (definitions[objectId] != null) {
						definitions[objectId].setDescription(examine);
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item examinations.");
	}
	
	private static void parseItemTradeable() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-tradable.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = Integer.parseInt(parsed[0].trim());
			try {
				String examine = parsed[1].trim();
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setTradeable(examine.equalsIgnoreCase("yes"));
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item tradeable information.");
	}
	
	private static void parseItemMembers() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-members.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int itemId = Integer.parseInt(parsed[0].trim());
			try {
				String examine = parsed[1].trim();
				if (itemId < count()) {
					if (definitions[itemId] != null) {
						definitions[itemId].setMembers(examine.equalsIgnoreCase("yes"));
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item membership information.");
	}

	private static void parseItemWeight() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/item-weight.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int objectId = 0;
			try {
				objectId = Integer.parseInt(parsed[0].trim());
			} catch (Exception e) {
				/* Skip, was error when dumping */
				continue;
			}
			try {
				String examine = parsed[1].trim().replaceAll("&", "").replaceAll("kg", "");
				if (objectId < count()) {
					if (definitions[objectId] != null) {
						double weight;
						try {
							 weight = Double.parseDouble(examine);
						} catch (Exception e) {
							weight = 0;
						}
						definitions[objectId].setWeight(weight);
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " item weight information.");
	}
	
	public static int count() {
		return definitions.length;
	}

	public static ItemDefinition forId(int id) {
		return definitions[id];
	}

}
