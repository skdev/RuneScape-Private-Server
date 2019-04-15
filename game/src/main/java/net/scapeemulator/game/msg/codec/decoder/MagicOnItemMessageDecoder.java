package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.MagicOnItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class MagicOnItemMessageDecoder extends MessageDecoder<MagicOnItemMessage> {

	public MagicOnItemMessageDecoder() {
		super(Opcode.MAGIC_ON_ITEM);
	}

	@Override
	public MagicOnItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int spellBook = (int) reader.getUnsigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int itemId = (int) reader.getUnsigned(DataType.SHORT);
		int spellId = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int itemSlotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int params = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		return new MagicOnItemMessage(itemId, itemSlotId, spellId, spellBook);
	}

}
