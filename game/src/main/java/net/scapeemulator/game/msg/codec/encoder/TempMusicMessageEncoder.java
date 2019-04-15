package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.MusicMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class TempMusicMessageEncoder extends MessageEncoder<MusicMessage> {

	public TempMusicMessageEncoder() {
		super(MusicMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, MusicMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.TEMP_MUSIC);
		
		builder.put(DataType.SHORT, message.getId());
		builder.put(DataType.BYTE, DataTransformation.ADD, message.getVolume());
		
		return builder.toGameFrame();
	}
}
