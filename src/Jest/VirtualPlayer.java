package Jest;

import java.util.LinkedList;

public class VirtualPlayer extends Player{
private Level level;
private int number;
	
	public VirtualPlayer(int number,Level level) {
		super();
		this.level = level;
		this.number=number;
	}

	public int getnumber() {
		return this.number;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	public void StrategyOffer() {
		this.level.StrategyOffer(this);
		this.hastakencard = false;
	}
	
	public Player StrategyTake(LinkedList<Player> offeredPlayers) {
		if (offeredPlayers.size()>1) {
			offeredPlayers.remove(this);	
		}	
		
		this.hastakencard = true;
		
		System.out.println("!!!!VIRTUAL TAKE!!!!!");
		
		return this.level.StrategyTake(offeredPlayers,this);
		
		

	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Virtual player ");
		sb.append(this.number);
	
		return sb.toString();
	}
}
