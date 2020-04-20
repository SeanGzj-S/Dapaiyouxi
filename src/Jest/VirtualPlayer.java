package Jest;

import java.util.LinkedList;
/**
 * {@code VirtualPlayer} is a class which represents for human players.
 * As virtual player is a player, it extends the class {@code Player}.
 */
public class VirtualPlayer extends Player{
	/**
	 * The virtual player has a level {@code Level} (easy, normal, tough).
	 */
    private Level level;
    /**
     * The virtual player has a number.
     */
    private int number;
	
    /**
     * Constructor of the virtual player.
     * @param number the number to set.
     * @param level the level to set.
     */
	public VirtualPlayer(int number,Level level) {
		super();
		this.level = level;
		this.number=number;
	}
	/**
	 * To get the number of the virtual player.
	 * @return type of interger, the number of the virtual player.
	 */
	public int getnumber() {
		return this.number;
	}
	/**
	 * To get the level of the virtual player.
	 * @return type of {@code Level}, the level of the virtual player.
	 */
	public Level getLevel() {
		return level;
	}
	/**
	 * To set the level of the virtual player.
	 * @param level the level to set.
	 */
	public void setLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * Call the function {@code StrategyOffer(VirtualPlayer vp)}
	 */
	public void StrategyOffer() {
		this.level.StrategyOffer(this);
		this.hastakencard = false;
	}
	
	/**
	 * Call the function {@code StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp)}
	 * @param offeredPlayers is the list of players from whom the virtual player can take offer.
	 * @return type of {@code Player} who will be the next player to take offers.
	 */
	public Player StrategyTake(LinkedList<Player> offeredPlayers) {
		if (offeredPlayers.size()>1) {
			offeredPlayers.remove(this);	
		}	
		
		this.hastakencard = true;
		
		System.out.println("!!!!VIRTUAL TAKE!!!!!");
		
		return this.level.StrategyTake(offeredPlayers,this);
		
		

	}
	
	/**
	 * Overwrite the function toString() to show the information of the virtual player.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Virtual player ");
		sb.append(this.number);
	
		return sb.toString();
	}
}
