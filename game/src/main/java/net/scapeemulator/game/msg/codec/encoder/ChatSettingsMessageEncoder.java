package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ChatSettingMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class ChatSettingsMessageEncoder extends MessageEncoder<ChatSettingMessage> {

	public ChatSettingsMessageEncoder() {
		super(ChatSettingMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ChatSettingMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 23);
		builder.put(DataType.BYTE, message.getFriends());
		builder.put(DataType.BYTE, message.getClan());
		builder.put(DataType.BYTE, message.getTrade());
		return builder.toGameFrame();
	}

}
