package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.GroundItemMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class GroundItemMessageEncoder extends MessageEncoder<GroundItemMessage> {

	public GroundItemMessageEncoder() {
		super(GroundItemMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, GroundItemMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.CREATE_GROUND_ITEM);
		
		builder.put(DataType.BYTE, DataTransformation.ADD, message.getPositon().blockHash());
		builder.put(DataType.SHORT, DataOrder.LITTLE, message.getItem().getId());
		builder.put(DataType.SHORT, DataTransformation.ADD, message.getItem().getAmount());
		
		return builder.toGameFrame();
	}


}
