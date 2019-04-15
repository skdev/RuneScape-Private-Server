package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.FriendMessage;

public class AddFriendMessageHandler extends MessageHandler<FriendMessage> {

	@Override
	public void handle(Player player, FriendMessage message) {
		player.getFriends().addFriend(message.getName());
	}

}
