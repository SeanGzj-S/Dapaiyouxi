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
	
	public void StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
				
		Card theSmallestOne = offeredPlayers.get(0).getfaceupOffer();
		if (theSmallestOne instanceof Joker) {
			vp.addToJest(theSmallestOne);
			offeredPlayers.get(0).offer.remove(theSmallestOne);
		} else {
			SuitCard theSmallestOne1 = (SuitCard) theSmallestOne;
			for(Player player : offeredPlayers) {
				if (player.getfaceupOffer() instanceof Joker) {
					theSmallestOne = player.getfaceupOffer();
				} else {
					SuitCard compareOne = (SuitCard) player.getfaceupOffer();
					if (compareOne.compare(theSmallestOne1) == false) {
						theSmallestOne = player.getfaceupOffer();
					}	
				}
				vp.addToJest(theSmallestOne);
				player.offer.remove(theSmallestOne);
				}
			}
		}
	
	
	
	
	
}
