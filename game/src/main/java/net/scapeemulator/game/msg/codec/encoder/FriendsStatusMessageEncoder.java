package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.FriendsStatusMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class FriendsStatusMessageEncoder extends MessageEncoder<FriendsStatusMessage> {

	public FriendsStatusMessageEncoder() {
		super(FriendsStatusMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, FriendsStatusMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.FRIENDS_STATUS);
		builder.put(DataType.BYTE, message.getType());
		return builder.toGameFrame();
	}

}
