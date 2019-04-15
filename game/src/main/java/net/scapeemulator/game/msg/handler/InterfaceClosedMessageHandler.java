package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.InterfaceClosedMessage;

public final class InterfaceClosedMessageHandler extends MessageHandler<InterfaceClosedMessage> {

	@Override
	public void handle(Player player, InterfaceClosedMessage message) {
		
		if (player.getInterfaceSet().allTabsClosed()) {
			player.getInterfaceSet().openAllTabs();
		}
		
		if (player.getTrade().isTrading()) {
			player.getTrade().getSession().declineTrade();
			return;
		}
		
	}

}
