package net.scapeemulator.game.model.music;

public class Music {
	private final int id, regionId;
	private final String name;
	
	private boolean locked;

	public Music(int id, int regionId, String name) {
		this.name = name;
		this.id = id;
		this.regionId = regionId;
		locked = true;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public int getRegionId() {
		return regionId;
	}

	public boolean isLocked() {
		return locked;
	}

	public void unlock() {
		locked = false;
	}
}
