package net.scapeemulator.game.msg;

public class MusicVolumeMessage extends Message {
	private int volume;
	
	public MusicVolumeMessage(int volume) {
		this.volume = volume;
	}
	
	public int getVolume() {
		return volume;
	}
}
