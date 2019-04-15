package net.scapeemulator.game.model.combat;

public class Hit {
	private HitType type;
	private int damage;
	
	public Hit(int damage, HitType type) {
		this.type = type;
		this.damage = damage;
	}
	
	public HitType getType() {
		return type;
	}
	
	public int getDamage() {
		return damage;
	}
}
