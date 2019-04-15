package net.scapeemulator.game.msg;

public class ChatSettingMessage extends Message {
	private final int friends, clan, trade;
	
	public ChatSettingMessage(int friends, int clan, int trade) {
		this.friends = friends;
		this.clan = clan;
		this.trade = trade;
	}

	public int getFriends() {
		return friends;
	}

	public int getClan() {
		return clan;
	}

	public int getTrade() {
		return trade;
	}
}
