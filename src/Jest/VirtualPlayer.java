package Jest;

import java.util.LinkedList;

public class VirtualPlayer extends Player{
private Level level;
	
	public VirtualPlayer(int number,Level level) {
		super();
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	public void StrategyOffer() {
		this.level.StrategyOffer(this);
	}
	
	public void StrategyTake(LinkedList<Player> offeredPlayers) {
		if (offeredPlayers.size()>1) {
			offeredPlayers.remove(this);	
		}	
		this.level.StrategyTake(offeredPlayers,this);
	}
}
