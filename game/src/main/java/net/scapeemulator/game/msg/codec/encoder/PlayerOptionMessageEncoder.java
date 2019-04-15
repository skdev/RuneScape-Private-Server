package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.PlayerOptionMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class PlayerOptionMessageEncoder extends MessageEncoder<PlayerOptionMessage> {

	public PlayerOptionMessageEncoder() {
		super(PlayerOptionMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, PlayerOptionMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 126);
		builder.putString(message.getOption());
		builder.put(DataType.BYTE, DataTransformation.NEGATE, 1);
		builder.put(DataType.SHORT, DataOrder.LITTLE, 1);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, 1);
		return builder.toGameFrame();
	}

}
