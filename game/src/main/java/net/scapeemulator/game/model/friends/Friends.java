package net.scapeemulator.game.model.friends;

import java.util.ArrayList;
import java.util.List;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.util.Utils;

public class Friends {
	private FriendStatus status = FriendStatus.ONLINE;
	private List<Long> friends = new ArrayList<>();
	private List<Long> ignore = new ArrayList<>();
	
	public void addFriend(long player) {
		friends.add(player);
	}
	
	public void deleteFriend(long name) {
		friends.remove(name);
	}
	
	public void addIgnore(long name) {
		if (!ignore.contains(name)) {
			if (friends.contains(name)) {
				friends.remove(name);
			}
			ignore.add(name);
		}
	}
	
	public void deleteIgnore(Player name) {
		if (ignore.contains(name)) {
			ignore.remove(name);
		}
	}
	
	public boolean containsFriend(Player player) {
		return friends.contains(player);
	}
	
	public List<Long> getFriends() {
		return friends;
	}
	
	public List<Long> getIgnore() {
		return ignore;
	}
	
	public FriendStatus getStatus() {
		return status;
	}
	
	public void setStatus(FriendStatus status) {
		this.status = status;
	}
}
