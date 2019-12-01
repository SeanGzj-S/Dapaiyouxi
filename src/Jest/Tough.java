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
	
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
		Player nextPlayer = null;
		
		Card theBiggestOne = offeredPlayers.get(0).getfaceupOffer();
		nextPlayer= offeredPlayers.get(0);
		if (theBiggestOne instanceof Joker) {
			theBiggestOne = offeredPlayers.get(1).getfaceupOffer();
		}
		for (Player player : offeredPlayers) {
			SuitCard theBiggestOne1 = (SuitCard) theBiggestOne;
			if (!(player.getfaceupOffer() instanceof Joker)) {
				SuitCard compareOne = (SuitCard) player.getfaceupOffer();
				if (compareOne.compare(theBiggestOne1) == true) {
					theBiggestOne = player.getfaceupOffer();
				}	
			}
			vp.addToJest(theBiggestOne);
			player.offer.remove(theBiggestOne);
		}
	
	return nextPlayer;
	}///???
}
