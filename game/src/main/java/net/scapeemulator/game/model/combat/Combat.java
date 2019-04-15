package net.scapeemulator.game.model.combat;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.model.item.Equipment;

public class Combat {
	private Player player;
	private boolean attacking;
	
	private AttackTask attackTask;
	
	public Combat(Player player) {
		this.player = player;
	}
	
	public void attack(Player target) {
		if (target.getCombat().inCombat() || inCombat()) {
			return;
		}
		
		if (attackTask == null) {
			attackTask = new AttackTask(player, target, (int) player.getEquipment().get(Equipment.WEAPON).getDefinition().getSpeed());
			World.getWorld().getTaskScheduler().schedule(attackTask);
		}
		
		attackTask.setDelay((int) player.getEquipment().get(Equipment.WEAPON).getDefinition().getSpeed());
		
		attacking = true;
	}
	
	public void stopAttacking() {
		attackTask = null;
		attacking = false;
	}
	
	public boolean inCombat() {
		return attacking;
	}
}
