package net.scapeemulator.game.model.combat;

public class HitSecond {
	private HitType type;
	private int damage;
	
	public HitSecond(int damage, HitType type) {
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
