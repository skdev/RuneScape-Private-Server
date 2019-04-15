package net.scapeemulator.game.msg;

public class MagicOnItemMessage extends Message {
	private final int itemId, slot, spellId, spellBook;
	
	public MagicOnItemMessage(int itemId, int slot, int spellId, int spellBook) {
		this.itemId = itemId;
		this.slot = slot;
		this.spellId = spellId;
		this.spellBook = spellBook;
	}

	public int getItemId() {
		return itemId;
	}

	public int getSlot() {
		return slot;
	}

	public int getSpellId() {
		return spellId;
	}

	public int getSpellBook() {
		return spellBook;
	}
}
