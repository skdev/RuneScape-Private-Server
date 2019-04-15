package net.scapeemulator.game.model.friends;

public enum FriendStatus {
	OFFLINE(0), ONLINE(1), FRIENDS_ONLY(2);
	
	private int status;
	
	FriendStatus(int status) {
		this.status = status;
	}
	
	public int getType() {
		return status;
	}
}
