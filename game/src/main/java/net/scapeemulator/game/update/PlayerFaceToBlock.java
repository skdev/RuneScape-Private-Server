package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class PlayerFaceToBlock extends PlayerBlock {
	private Player player;
	
	public PlayerFaceToBlock(Player player) {
		super(0x40);
		this.player = player;
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, DataOrder.LITTLE, 3230);
		builder.put(DataType.SHORT, 3235);
	}

}
