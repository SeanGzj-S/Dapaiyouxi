package Jest;

import java.util.LinkedList;

/**
 * The interface {@code Easy} is a part of the design pattern Strategy, it implements the interface {@code Level}
 * Inside the interface, the two functions will be implemented.
 */
public class Easy implements Level{
	
	/**
	 * The virtual player makes offers.
	 * In the class Card, a function <<compareCard>> is defined for the strategy design pattern.
	 * It returns a boolean to show which one of the two cards is the better one to take.
	 * In the easy level, the virtual player tend to face up the better card to other players.
	 * @param vp is the virtual player who makes offers.
	 */
	public void StrategyOffer(VirtualPlayer vp) {
		Card offer1 = vp.getOffer().get(0);
		Card offer2 = vp.getOffer().get(1);
		if (offer1.compareCard(offer2) == true) {
			vp.setFaceUp(offer1);
		}
		else {vp.setFaceUp(offer2);}
	}
	
	
	/**
	 * The virtual player takes offer.
	 * In the easy level, for all the available offers, the virtual player compare one by one the faced up one.
	 * It will take the worst one.
	 * @param offeredPlayers is the list of players from whom the virtual player can take offer.
	 * @param vp is the virtual player who takes offers
	 * @return type of {@code Player} who will be the next player to take offers.
	 */
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
		Player nextPlayer = null;
		Card theWorstOne = offeredPlayers.get(0).getfaceupOffer();
		for (int i=1;i<offeredPlayers.size();i++) {
			Card comparedOne = offeredPlayers.get(i).getfaceupOffer();
			if (theWorstOne.compareCard(comparedOne) == true) {
				theWorstOne = offeredPlayers.get(i).getfaceupOffer();
			}
		}
		vp.addToJest(theWorstOne);
		for (Player player : offeredPlayers) {
			if (player.getOffer().contains(theWorstOne)){
				player.offer.remove(theWorstOne);
				nextPlayer = player;
				
			}
		}
		vp.hastakencard = true;
		System.out.println("The virtual player " + vp.getnumber() + " has taken an offer of " + nextPlayer);
		return nextPlayer;
	}
}
