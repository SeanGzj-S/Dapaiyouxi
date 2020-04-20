package Jest;

import java.util.LinkedList;
/**
 * The interface {@code Normal} is a part of the design pattern Strategy, it implements the interface {@code Level}
 * Inside the interface, the two functions will be implemented.
 */
public class Normal implements Level{

	/**
	 * The virtual player makes offers.
	 * In the easy level, the virtual player face up the one of the two cards randomly to other players.
	 * @param vp is the virtual player who makes offers.
	 */
	public void StrategyOffer(VirtualPlayer vp) {
		int randomoffer = (int)(Math.random());
		vp.getOffer().get(randomoffer).faceup();		
	}
	
	/**
	 * The virtual player takes offer.
	 * In the easy level, for all the available offers, the virtual player take one randomly.
	 * @param offeredPlayers is the list of players from whom the virtual player can take offer.
	 * @param vp is the virtual player who takes offers
	 * @return type of {@code Player} who will be the next player to take offers.
	 */
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
		int randomoffer = (int)(Math.random()*2);
		int randomplayer = (int)(Math.random()*offeredPlayers.size());
		

			vp.addToJest(offeredPlayers.get(randomplayer).offer.get(randomoffer));
			offeredPlayers.get(randomplayer).offer.remove(randomoffer);
	
			return offeredPlayers.get(randomplayer);
	
	}

	
	
}
