package net.scapeemulator.game.msg;

public class FriendsStatusMessage extends Message {
	private final int type;
	
	public FriendsStatusMessage(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
