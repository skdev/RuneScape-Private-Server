package net.scapeemulator.game.msg;

public class MapBlackoutMessage extends Message {
	private final int status;
	
	public MapBlackoutMessage(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}
