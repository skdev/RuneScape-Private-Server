package net.scapeemulator.game.msg;

public class MusicPlayingMessage extends Message {
	private final int id;
	
	public MusicPlayingMessage(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
