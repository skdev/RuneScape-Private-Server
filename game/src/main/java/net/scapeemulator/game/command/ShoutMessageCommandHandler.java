package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;

public class ShoutMessageCommandHandler extends CommandHandler {

	public ShoutMessageCommandHandler(String name) {
		super(name);
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (arguments.length <= 0) {
			player.sendMessage("Syntax: ::yell msg");
			return;
		}
		
		String message = arguments[0];
		
		if (player.getRights() == 1) {
			World.getWorld().getPlayers().forEach(p -> {
				p.sendMessage("[Moderator] " + player.getUsername() + ": " + message);
			});
			return;
		} else if (player.getRights() == 2) {
			World.getWorld().getPlayers().forEach(p -> {
				p.sendMessage("[Administrator] " + player.getUsername() + ": " + message);
			});
			return;
		}
		
		World.getWorld().getPlayers().forEach(p -> {
			p.sendMessage("[Player] " + player.getUsername() + ": " + message);
		});
		
		/* player.send(new ServerMessage("<shad=2><trans=50><col=493>Hello World")); */
	}

}
