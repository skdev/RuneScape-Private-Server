package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.WalkingQueue;
import net.scapeemulator.game.msg.NpcAttackMessage;
import net.scapeemulator.game.pf.AStarPathFinder;
import net.scapeemulator.game.pf.Path;
import net.scapeemulator.game.pf.PathFinder;

public class NpcAttackMessageHandler extends MessageHandler<NpcAttackMessage> {
	private final PathFinder pathFinder = new AStarPathFinder();
	 
	@Override
	public void handle(Player player, NpcAttackMessage message) {
		Npc npc = message.getNpc();
		pf_check:
        {
            if(player.getPosition().isWithinDistance(npc.getPosition(), 1))
                break pf_check;

            WalkingQueue queue = player.getWalkingQueue();
            Path path = pathFinder.find(player, npc.getPosition().getX(), npc.getPosition().getY());
            if(path != null) {
                Position first = path.poll();
                if(first == null)
                    break pf_check;
                
                queue.addFirstStep(first);
                player.stopAction();

                while(!path.getPoints().isEmpty()) {
                    Position step = path.poll();
                    queue.addStep(step);
                }
            }
        }
	}

}
