package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.MusicVolumeMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class MusicVolumeMessageDecoder extends MessageDecoder<MusicVolumeMessage> {

	public MusicVolumeMessageDecoder() {
		super(Opcode.MUSIC_VOLUME_CHANGE);
	}

	@Override
	public MusicVolumeMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int volume = (int) reader.getSigned(DataType.INT);
		return new MusicVolumeMessage(volume);
	}

}
