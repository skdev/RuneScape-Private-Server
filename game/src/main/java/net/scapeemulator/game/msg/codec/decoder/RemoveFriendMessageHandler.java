package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.FriendMessage;
import net.scapeemulator.game.msg.handler.MessageHandler;

public class RemoveFriendMessageHandler extends MessageHandler<FriendMessage>{

	@Override
	public void handle(Player player, FriendMessage message) {
		player.getFriends().deleteFriend(message.getName());
	}

}
