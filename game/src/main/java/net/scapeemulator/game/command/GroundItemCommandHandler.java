package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.GroundItemMessage;

public class GroundItemCommandHandler extends CommandHandler {

	public GroundItemCommandHandler() {
		super("gitem");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (arguments.length != 2) {
			player.sendMessage("Syntax: ::gitem id amt");
			return;
		}
		
		int id = Integer.parseInt(arguments[0]);
		int amt = Integer.parseInt(arguments[1]);
		
		player.send(new GroundItemMessage(new Item(id, amt), player.getPosition()));
	}

}
