package net.scapeemulator.game.msg;

public class MusicMessage extends Message {
	private int id, volume;
	
	public MusicMessage(int id) {
		this.id = id;
	}
	
	public MusicMessage(int id, int volume) {
		this.id = id;
		this.volume = volume;
	}
	
	public int getId() {
		return id;
	}
	
	public int getVolume() {
		return volume;
	}
}
