package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.model.friends.FriendStatus;
import net.scapeemulator.game.msg.UpdateFriendMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;
import net.scapeemulator.game.util.Utils;

public class UpdateFriendsMessageEncoder extends MessageEncoder<UpdateFriendMessage> {

	public UpdateFriendsMessageEncoder() {
		super(UpdateFriendMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, UpdateFriendMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.UPDATE_FRIEND, Type.VARIABLE_BYTE);
		
		Player target = World.getWorld().getPlayerByName(message.getName());
		FriendStatus status = FriendStatus.OFFLINE;
		
		if (target != null) {
			status = target.getFriends().getStatus();
		}
		
		if (status == FriendStatus.FRIENDS_ONLY) {
			if (message.getPlayer().getFriends().containsFriend(target) && target.getFriends().containsFriend(message.getPlayer())) {
				if (status != FriendStatus.OFFLINE)
					status = FriendStatus.ONLINE;
			}
		}
		
		builder.put(DataType.LONG, Utils.stringToLong(message.getName())); //name
		builder.put(DataType.SHORT, status.getType()); //status
		builder.put(DataType.BYTE, 2); //World
		builder.putString("World 1"); //world
		
		return builder.toGameFrame();
	}

}
