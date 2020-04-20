package Jest;

import java.util.LinkedList;

/**
 * The interface {@code Level} is a part of the design pattern Strategy.
 * Inside the interface, two functions are declared
 */
public interface Level {
	
	/**
	 * The virtual player makes offers.
	 * @param vp is the virtual player who makes offers.
	 */
	public void StrategyOffer(VirtualPlayer vp);
	
	/**
	 * The virtual player takes offer.
	 * @param offeredPlayers is the list of players from whom the virtual player can take offer.
	 * @param vp is the virtual player who takes offers
	 * @return type of {@code Player} who will be the next player to take offers.
	 */
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp);
	
}
