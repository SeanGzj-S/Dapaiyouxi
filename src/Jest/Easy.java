package Jest;

import java.util.LinkedList;

public class Easy implements Level{
	public void StrategyOffer(VirtualPlayer vp) {
		Card offer1 = vp.getOffer().get(0);
		Card offer2 = vp.getOffer().get(1);
		if (offer1 instanceof Joker) {
			offer2.faceup();;
		} else if (offer2 instanceof Joker) {
			offer1.faceup();
		} else if (offer1 instanceof SuitCard && offer2 instanceof SuitCard) {
			SuitCard offer11 = (SuitCard)offer1;
			SuitCard offer22 = (SuitCard)offer2;
			if (offer11.compare(offer22) == true) {
				offer1.faceup();
			} else {
				offer2.faceup();
			}
		}
			
	}
	

				
		/*
		 * In the easy level, for all the available offers, the virtual player compare one by one the faced up one.
		 * It will take the worst one.
		 */
		public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
			Player nextPlayer = null;
			Card theWorstOne = offeredPlayers.get(0).getfaceupOffer();
			for (Player player : offeredPlayers) {
				Card comparedOne = player.getfaceupOffer();
				if (theWorstOne.compareCard(comparedOne) == true) {
					theWorstOne = player.getfaceupOffer();
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
			System.out.println("The virtual player " + vp.getnumber() + "has taken an offer of " + nextPlayer);
			return nextPlayer;
		
		}
	
	
	
	
}
