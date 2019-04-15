package net.scapeemulator.game.msg;

public class FriendMessage extends Message {
	private long name;
	
	public FriendMessage(long nameLong) {
		name = nameLong;
	}
	
	public long getName() {
		return name;
	}
}
