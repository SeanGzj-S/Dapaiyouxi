package Jest;

import java.util.LinkedList;

public class Normal implements Level{

	public void StrategyOffer(VirtualPlayer vp) {
		int randomoffer = (int)(Math.random());
		vp.getOffer().get(randomoffer).faceup();		
	}
	
	public Player StrategyTake(LinkedList<Player> offeredPlayers,VirtualPlayer vp) {
		int randomoffer = (int)(Math.random()*2);
		int randomplayer = (int)(Math.random()*offeredPlayers.size());
		

			vp.addToJest(offeredPlayers.get(randomplayer).offer.get(randomoffer));
			offeredPlayers.get(randomplayer).offer.remove(randomoffer);
	
			return offeredPlayers.get(randomplayer);
	
	}

	
	
}
