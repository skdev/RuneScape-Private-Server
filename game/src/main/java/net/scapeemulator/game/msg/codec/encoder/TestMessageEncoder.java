package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.msg.TestMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;
import net.scapeemulator.game.util.Utils;

public class TestMessageEncoder extends MessageEncoder<TestMessage> {

	public TestMessageEncoder() {
		super(TestMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, TestMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 182, Type.VARIABLE_BYTE);
		
		Player target = null;
		try {
			target = World.getWorld().getPlayerByName("hello");
		} catch (Exception e) {
			/* Ignore */
		}
		int status = 0;
		
		if (target != null) {
			status = 1;
		}
		
		
		System.out.println("" + Utils.stringToLong(target.getUsername()));
		builder.put(DataType.LONG, Utils.stringToLong(target.getUsername())); //name
		builder.put(DataType.SHORT, status); //status
		builder.put(DataType.BYTE, 1);
		builder.putString("World 1");
		
		return builder.toGameFrame();
	}

}
