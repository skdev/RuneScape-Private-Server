package net.scapeemulator.game.msg;

public class PrivateMessage extends Message {
	private final long message;
	
	public PrivateMessage(long message) {
		this.message = message;
	}

	public long getMessage() {
		return message;
	}
	
}
