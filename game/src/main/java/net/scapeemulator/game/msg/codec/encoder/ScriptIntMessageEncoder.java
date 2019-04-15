package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ScriptIntMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;

import java.io.IOException;

public final class ScriptIntMessageEncoder extends MessageEncoder<ScriptIntMessage> {

	public ScriptIntMessageEncoder() {
		super(ScriptIntMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ScriptIntMessage message) throws IOException {
		int id = message.getId();
		int value = message.getValue();
		
		if (value <= 128 && value >= 127) {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, 176);
			builder.put(DataType.SHORT, 0);
			builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, id);
			builder.put(DataType.BYTE, DataTransformation.NEGATE, value);
			System.out.println("Sending value:" + value + " packet:176");
			return builder.toGameFrame();
		} else {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, 148);
			builder.put(DataType.SHORT, id);
			builder.put(DataType.INT, DataOrder.LITTLE, value);
			builder.put(DataType.SHORT, 0);
			return builder.toGameFrame();
		}
	}

}
