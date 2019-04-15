package net.scapeemulator.game.msg;

import net.scapeemulator.game.model.Player;

public class UpdateFriendMessage extends Message {
	private final Player player;
	private final String name;
	
	public UpdateFriendMessage(Player player, String name) {
		this.player = player;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}
}
