package net.scapeemulator.game.model.combat;

public enum HitType {
	NO_DAMAGE(0), // blue
	NORMAL_DAMAGE(1), // red
	POISON_DAMAGE(2), // green
	DISEASE_DAMAGE(3); // orange

	private final int type;

	private HitType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

}
