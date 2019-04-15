package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.FriendMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class RemoveFriendMessageDecoder extends MessageDecoder<FriendMessage> {

	public RemoveFriendMessageDecoder() {
		super(Opcode.DELETE_FRIEND);
	}

	@Override
	public FriendMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		long nameLong = reader.getSigned(DataType.LONG);
		return new FriendMessage(nameLong);
	}

}
