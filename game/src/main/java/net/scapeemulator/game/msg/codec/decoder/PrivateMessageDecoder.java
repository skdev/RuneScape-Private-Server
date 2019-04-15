package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.PrivateMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class PrivateMessageDecoder extends MessageDecoder<PrivateMessage> {

	public PrivateMessageDecoder() {
		super(Opcode.PRIVATE_MESSAGE);
	}

	@Override
	public PrivateMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
		long message = reader.getSigned(DataType.LONG);
		return new PrivateMessage(message);
	}

}
