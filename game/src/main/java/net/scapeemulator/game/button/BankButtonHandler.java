package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;

public class BankButtonHandler extends ButtonHandler {
	private static final int BANK_INVENT_BUTTON = 20;
	private static final int BANK_EQUIPMENT_BUTTON = 22;
	private static final int BANK_SUMMONING_BUTTON = 22;
	private static final int SWITCH_TO_NOTE = 18;
	
	public BankButtonHandler() {
		super(Interface.BANK_INTERFACE);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == BANK_INVENT_BUTTON) {
			Item[] invent = player.getInventory().toArray();
			for (Item item : invent) {
				if (item != null) {
					player.getInventory().remove(item);
					player.getBank().add(item);
				}
			}
		} else if (slot == BANK_EQUIPMENT_BUTTON) {
			Item[] equipment = player.getEquipment().toArray();
			for (Item item : equipment) {
				if (item != null) {
					player.getEquipment().remove(item);
					player.getBank().add(item);
				}
			}
		} else if (slot == BANK_SUMMONING_BUTTON) {
			
		}
	}

}
