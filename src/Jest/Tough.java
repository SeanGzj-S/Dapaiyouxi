package Jest;

import java.util.LinkedList;

public class Tough implements Level{
	public void StrategyOffer(VirtualPlayer vp) {
		Card offer1 = vp.getOffer().get(0);
		Card offer2 = vp.getOffer().get(1);
		if (offer1 instanceof Joker) {
			offer1.faceup();
		} else if (offer2 instanceof Joker) {
			offer2.faceup();;
		} else if (offer1 instanceof SuitCard && offer2 instanceof SuitCard) {
			SuitCard offer11 = (SuitCard)offer1;
			SuitCard offer22 = (SuitCard)offer2;
			if (offer11.compare(offer22) == true) {
				offer2.faceup();
			} else {
				offer1.faceup();
			}
		}
			
	}
	
	/*
	 * In the tough level, for all the available offers, the virtual player compare one by one the faced up one.
	 * It will take the best one.
	 */
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
		Player nextPlayer = null;
		Card theBestOne = offeredPlayers.get(0).getfaceupOffer();
		for (Player player : offeredPlayers) {
			Card comparedOne = player.getfaceupOffer();
			if (theBestOne.compareCard(comparedOne) == false) {
				theBestOne = player.getfaceupOffer();
				nextPlayer = player;
			}
		}
		vp.addToJest(theBestOne);
		for (Player player : offeredPlayers) {
			if (player.getOffer().contains(theBestOne)){
				player.offer.remove(theBestOne);
				nextPlayer = player;
				System.out.println("The virtual player " + vp.getnumber() + " has taken an offer of " + player);
			}
		}
		vp.hastakencard = true;
		return nextPlayer;
		
	}
}
