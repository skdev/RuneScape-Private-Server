package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.MusicPlayingMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class MusicPlayingMessageDecoder extends MessageDecoder<MusicPlayingMessage> {

	public MusicPlayingMessageDecoder() {
		super(Opcode.MUSIC);
	}

	@Override
	public MusicPlayingMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int id = (int) reader.getSigned(DataType.INT);
		return new MusicPlayingMessage(id);
	}

}
