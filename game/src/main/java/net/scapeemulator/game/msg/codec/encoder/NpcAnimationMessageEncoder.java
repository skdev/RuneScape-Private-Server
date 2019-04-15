package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.NpcAnimationMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class NpcAnimationMessageEncoder extends MessageEncoder<NpcAnimationMessage>{

	public NpcAnimationMessageEncoder() {
		super(NpcAnimationMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, NpcAnimationMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.NPC_ANIMATION);
		builder.put(DataType.SHORT, message.getAnimationId());
		builder.put(DataType.SHORT, DataTransformation.ADD, message.getNpcIndex());
		builder.put(DataType.BYTE, DataTransformation.NEGATE, message.getDelay());
		return builder.toGameFrame();
	}

}
