package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Skill;
import net.scapeemulator.game.model.combat.Hit;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class HitPlayerBlock extends PlayerBlock {
	private Player player;
	
	public HitPlayerBlock(Player player) {
		super(0x1);
		this.player = player;
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		Hit hit = player.getNextHit();
		
		builder.put(DataType.BYTE, hit.getDamage()); //damage - this may be wrong
		builder.put(DataType.BYTE, hit.getType().getType()); //type
		
		int ratio = player.getSkillSet().getCurrentLevel(Skill.HITPOINTS) * 255 / player.getSkillSet().getLevel(Skill.HITPOINTS);
		builder.put(DataType.BYTE, DataTransformation.NEGATE, ratio); //ratio
	}

}
