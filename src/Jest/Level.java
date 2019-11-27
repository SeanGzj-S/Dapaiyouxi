package Jest;

import java.util.LinkedList;

public interface Level {
	
	public void StrategyOffer(VirtualPlayer vp);
	public void StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp);
	
}
