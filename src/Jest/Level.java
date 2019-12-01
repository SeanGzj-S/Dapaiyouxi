package Jest;

import java.util.LinkedList;

public interface Level {
	
	public void StrategyOffer(VirtualPlayer vp);
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp);
	
}
