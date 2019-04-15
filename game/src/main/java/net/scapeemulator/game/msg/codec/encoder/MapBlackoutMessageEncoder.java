package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.MapBlackoutMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class MapBlackoutMessageEncoder extends MessageEncoder<MapBlackoutMessage> {

	public MapBlackoutMessageEncoder() {
		super(MapBlackoutMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, MapBlackoutMessage message) throws IOException {
		final GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.MAP_BLACK_OUT);
		builder.put(DataType.BYTE, message.getStatus());
		return builder.toGameFrame();
	}

}
