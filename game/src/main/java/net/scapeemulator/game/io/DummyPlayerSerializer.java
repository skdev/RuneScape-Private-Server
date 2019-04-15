package net.scapeemulator.game.io;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.msg.UpdateFriendMessage;
import net.scapeemulator.game.net.login.LoginResponse;
import net.scapeemulator.game.util.Utils;

public final class DummyPlayerSerializer extends PlayerSerializer {

	@Override
	public SerializeResult load(String username, String password) {
		Player player = new Player();
		player.setUsername(username);
		player.setPassword(password);
		player.setRights(2);
		player.setPosition(new Position(3232, 3232));
		return new SerializeResult(LoginResponse.STATUS_OK, player);
	}

	@Override
	public void save(Player player) {
		/* discard player */
	}

}
