package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.MusicMessage;

public class MusicCommandHandler extends CommandHandler {

	public MusicCommandHandler() {
		super("music");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (arguments.length != 2) {
			player.sendMessage("Syntax: ::music id volume");
			return;
		}
		int id = Integer.parseInt(arguments[0]);
		int volume = Integer.parseInt(arguments[1]);
		player.send(new MusicMessage(id, volume));
	}

}
