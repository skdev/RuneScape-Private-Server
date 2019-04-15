package net.scapeemulator.game.model;

import java.util.Arrays;

import net.scapeemulator.cache.def.ItemBonus;
import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.ConfigMessage;
import net.scapeemulator.game.msg.InterfaceCloseMessage;
import net.scapeemulator.game.msg.InterfaceItemsMessage;
import net.scapeemulator.game.msg.InterfaceOpenMessage;
import net.scapeemulator.game.msg.InterfaceRootMessage;
import net.scapeemulator.game.msg.InterfaceTextMessage;
import net.scapeemulator.game.msg.ScriptIntMessage;

public final class InterfaceSet {
	private boolean tabsClosed = false;
	
	public enum DisplayMode {
		FIXED, RESIZABLE;
	}

	private final Player player;
	private final int[] tabs = new int[15];
	private int fullscreen = -1;
	private DisplayMode mode = DisplayMode.FIXED;

	public InterfaceSet(Player player) {
		this.player = player;

		Arrays.fill(tabs, -1);
	}

	public DisplayMode getDisplayMode() {
		return mode;
	}

	public void setDisplayMode(DisplayMode mode) {
		this.mode = mode;
	}

	public void init() {
		// TODO close any windows/overlays/etc. that may be left open if not
		// reconnecting ?
		// also consider the display mode changing
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceRootMessage(Interface.FIXED));
			player.send(new InterfaceOpenMessage(Interface.FIXED, 81, 752, 1)); // chat
																				// box
			player.send(new InterfaceOpenMessage(Interface.FIXED, 18, 751, 1)); // chat
																				// options
			player.send(new InterfaceOpenMessage(752, 8, 137, 1)); // chat
																	// username
																	// & scroll
																	// bar
			player.send(new InterfaceOpenMessage(Interface.FIXED, 10, 754, 1)); // PM
																				// split
																				// chat

			player.send(new InterfaceOpenMessage(Interface.FIXED, 75, Interface.HITPOINTS_ORB, 1)); // hitpoints
																									// orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 76, Interface.PRAYER_ORB, 1)); // prayer
																									// orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 77, Interface.ENERGY_ORB, 1)); // energy
																									// orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 78, Interface.SUMMONING_ORB, 1)); // summoning
																									// orb
		} else {
			player.send(new InterfaceRootMessage(Interface.RESIZABLE));
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 70, 752, 1)); // chat
																					// box
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 23, 751, 1)); // chat
																					// options
			player.send(new InterfaceOpenMessage(752, 8, 137, 1)); // chat
																	// username
																	// & scroll
																	// bar
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 71, 754, 1)); // PM
																					// split
																					// chat
																					// (correct?)

			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 13, Interface.HITPOINTS_ORB, 1)); // hitpoints
																										// orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 14, Interface.PRAYER_ORB, 1)); // prayer
																										// orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 15, Interface.ENERGY_ORB, 1)); // energy
																										// orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 16, Interface.SUMMONING_ORB, 1)); // summoning
																										// orb
		}

		Equipment.openAttackTab(player);
		openTab(Tab.SKILLS, Interface.SKILLS);
		openTab(Tab.QUEST, Interface.QUESTS);
		openTab(Tab.INVENTORY, Interface.INVENTORY);
		openTab(Tab.EQUIPMENT, Interface.EQUIPMENT);
		openTab(Tab.PRAYER, Interface.PRAYER);
		openTab(Tab.MAGIC, Interface.MAGIC);
		openTab(Tab.FRIENDS, Interface.FRIENDS);
		openTab(Tab.IGNORES, Interface.IGNORES);
		openTab(Tab.CLAN, Interface.CLAN);
		openTab(Tab.SETTINGS, Interface.SETTINGS);
		openTab(Tab.EMOTES, Interface.EMOTES);
		openTab(Tab.MUSIC, Interface.MUSIC);
		openTab(Tab.LOGOUT, Interface.LOGOUT);
		
		// openTab(Tab.SUMMONING, Interface.SUMMONING);
		// for (int i = 0; i < 6; i++)
		// player.send(new InterfaceVisibleMessage(747, i, true));
	}

	public int getTab(int tab) {
		return tabs[tab];
	}

	public int getFullscreen() {
		return fullscreen;
	}

	public void openTab(int tab, int id) {
		tabs[tab] = id;
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 89 + tab, id, 1));
		} else {
			// 76 = force a single tab to be shown ?
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 93 + tab, id, 1));
		}
	}

	public void switchToTab(int tab) {
		player.send(new ScriptIntMessage(168, tab));
	}

	public void closeTab(int tab) {
		tabs[tab] = -1;
		if (mode == DisplayMode.FIXED) {

		} else {

		}
	}
	
	/*
	 * TODO: Fix
	 */
	public void closeAllTabs() {
		for (int i = 0; i < 15; i++) {
			tabs[i] = -1;
		}
		tabsClosed = true;
	}
	
	public void openAllTabs() {
		tabsClosed = false;
		Equipment.openAttackTab(player);
		openTab(Tab.SKILLS, Interface.SKILLS);
		openTab(Tab.QUEST, Interface.QUESTS);
		openTab(Tab.INVENTORY, Interface.INVENTORY);
		openTab(Tab.EQUIPMENT, Interface.EQUIPMENT);
		openTab(Tab.PRAYER, Interface.PRAYER);
		openTab(Tab.MAGIC, Interface.MAGIC);
		openTab(Tab.FRIENDS, Interface.FRIENDS);
		openTab(Tab.IGNORES, Interface.IGNORES);
		openTab(Tab.CLAN, Interface.CLAN);
		openTab(Tab.SETTINGS, Interface.SETTINGS);
		openTab(Tab.EMOTES, Interface.EMOTES);
		openTab(Tab.MUSIC, Interface.MUSIC);
		openTab(Tab.LOGOUT, Interface.LOGOUT);
	}
	
	public boolean allTabsClosed() {
		return tabsClosed;
	}
	
	public void openChatBoxInterface(int childId) {
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(752, 11, childId, 0)); // TODO:
																		// Fix
																		// order
		} else {
			// TODO: Resizable
		}
	}

	/*
	 * public void sendChatboxInterface(int childId) { sendInterface(0, 752, 11,
	 * childId); }
	 * 
	 * private void sendInterface(int show, int window, int inter, int child) {
	 * PacketBuf buf = new PacketBuf(35); buf.putByteC((byte) show); //type
	 * buf.putLEShortA(interfaceCount++); //0 buf.putLEInt(window << 16 |
	 * inter); //interfaceId buf.putLEShort(child); //childId }
	 */

	public void openWindow(int id) {
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 15, id, 0));
		} else {
			// TODO: id == 499 => slot 5 in xeno
			// TODO: another source: 3 norm, 4 for bank , 6 for help?
			// somewhere else it uses 8?
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 6, id, 0));
		}
	}

	public void openOverlay(int id) {
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 5, id, 1));
		} else {
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 5, id, 1));
		}
	}

	public void openFullscreen(int id) {
		fullscreen = id;
		player.send(new InterfaceRootMessage(id));
	}

	public void closeFullscreen() {
		fullscreen = -1;
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceRootMessage(Interface.FIXED));
		} else {
			player.send(new InterfaceRootMessage(Interface.RESIZABLE));
		}
	}

	public void closeWindow() {
		player.send(new InterfaceCloseMessage(Interface.FIXED, 15));
	}

	public void openCharDesign() {
		openWindow(771);
	}

	public void openWorldMap() {
		player.send(new ScriptIntMessage(622, player.getPosition().toPackedInt())); // map center
		player.send(new ScriptIntMessage(674, player.getPosition().toPackedInt())); // player position
		openFullscreen(Interface.WORLD_MAP);
	}

	public void openBank() {
		closeAllTabs();
		player.getBank().add(new Item(4151));
		
		Item[] bankItems = player.getBank().toArray();
		Item[] inventItems = player.getInventory().toArray();

		player.send(new ScriptIntMessage(563, 41946304));
		player.send(new ScriptIntMessage(1248, -2013265920));

		player.send(new InterfaceItemsMessage(-1, 64207, 95, bankItems));
		player.send(new InterfaceItemsMessage(-1, 64209, 93, inventItems));
		player.send(new InterfaceItemsMessage(149, 0, 93, inventItems));

		openWindow(Interface.BANK);
		openTab(Tab.INVENTORY, 763);
	}

	public void openEquipmentTab() {
		openWindow(667);
		updateEquipmentScreen();
	}

	public void updateEquipmentScreen() {
		Item[] inventory = player.getInventory().toArray();
		int inventoryWeight = 0;
		int attackStab = 0, attackSlash = 0, attackCrush = 0, attackMagic = 0, attackRanged = 0;
		int defenceStab = 0, defenceSlash = 0, defenceCrush = 0, defenceMagic = 0, defenceRanged = 0;
		int otherStrength = 0, otherRangedStrength = 0, otherPrayer = 0;

		for (Item item : inventory) {
			if (item != null) {
				inventoryWeight += item.getDefinition().getWeight();
			}
		}

		Item[] equipment = player.getEquipment().toArray();
		for (Item item : equipment) {
			if (item != null) {
				inventoryWeight += item.getDefinition().getWeight();
				attackStab += item.getDefinition().getAttackBonus(ItemBonus.STAB);
				attackSlash += item.getDefinition().getAttackBonus(ItemBonus.SLASH);
				attackCrush += item.getDefinition().getAttackBonus(ItemBonus.CRUSH);
				attackMagic += item.getDefinition().getAttackBonus(ItemBonus.MAGIC);
				attackRanged += item.getDefinition().getAttackBonus(ItemBonus.RANGED);

				defenceStab += item.getDefinition().getDefenceBonus(ItemBonus.STAB);
				defenceSlash += item.getDefinition().getDefenceBonus(ItemBonus.SLASH);
				defenceCrush += item.getDefinition().getDefenceBonus(ItemBonus.CRUSH);
				defenceMagic += item.getDefinition().getDefenceBonus(ItemBonus.MAGIC);
				defenceRanged += item.getDefinition().getDefenceBonus(ItemBonus.RANGED);

				otherStrength += item.getDefinition().getOtherBonus(ItemBonus.STRENGTH);
				otherRangedStrength += item.getDefinition().getOtherBonus(ItemBonus.RANGED_STRENGTH);
				otherPrayer += item.getDefinition().getOtherBonus(ItemBonus.PRAYER);
			}
		}

		// weight
		player.send(new InterfaceTextMessage(667, 32, inventoryWeight + " kg"));

		// attack bonus
		player.send(new InterfaceTextMessage(667, 36, "Stab: " + (attackStab >= 0 ? "+" : "") + attackStab));
		player.send(new InterfaceTextMessage(667, 37, "Slash: " + (attackSlash >= 0 ? "+" : "") + attackSlash));
		player.send(new InterfaceTextMessage(667, 38, "Crush: " + (attackCrush >= 0 ? "+" : "") + attackCrush));
		player.send(new InterfaceTextMessage(667, 39, "Magic: " + (attackMagic >= 0 ? "+" : "") + attackMagic));
		player.send(new InterfaceTextMessage(667, 40, "Ranged: " + (attackRanged >= 0 ? "+" : "") + attackRanged));

		// defence bonus
		player.send(new InterfaceTextMessage(667, 41, "Stab: " + (defenceStab >= 0 ? "+" : "") + defenceStab));
		player.send(new InterfaceTextMessage(667, 42, "Slash: " + (defenceSlash >= 0 ? "+" : "") + defenceSlash));
		player.send(new InterfaceTextMessage(667, 43, "Crush: " + (defenceCrush >= 0 ? "+" : "") + defenceCrush));
		player.send(new InterfaceTextMessage(667, 44, "Magic: " + (defenceMagic >= 0 ? "+" : "") + defenceMagic));
		player.send(new InterfaceTextMessage(667, 45, "Ranged: " + (defenceRanged >= 0 ? "+" : "") + defenceRanged));
		player.send(new InterfaceTextMessage(667, 46, "Summoning: +" + 0));

		// other bonus
		player.send(new InterfaceTextMessage(667, 48, "Strength: " + (otherStrength > -0 ? "+" : "") + otherStrength));
		player.send(new InterfaceTextMessage(667, 50,
				"Ranged Str..: " + (otherRangedStrength >= 0 ? "+" : "") + otherRangedStrength));
		player.send(new InterfaceTextMessage(667, 49, "Prayer: " + (otherPrayer >= 0 ? "+" : "") + otherPrayer));
	}

}
