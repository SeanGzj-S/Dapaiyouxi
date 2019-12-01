package Jest;

public class HumanPlayer extends Player {

	private String name;
	
	
    public HumanPlayer(String name) {
	       super();
	       this.name=name;	
    }	
    
    public String getname() {
    	return this.name;
    }
    
    
    
	//make one of the offers faced-up
	public void makeOffer(Card card) {
		if (this.offer.contains(card)) {
			for (Card c:offer) {
				if (c== (Card)card) {
					c.faceup();
				}
				
				
			}
		} else {
			System.out.println("You don't have this card in your offer, please choose again.\n");
		}
		
		this.hastakencard=false;
	}
	
	
	
	
	//choose a player and take a card (1=the faced-up one, 0=the faced-down one)
	public void takeOffer(Player player, int upOrDown) {
		if (player.offerIsComplete()) {
			if (upOrDown == 1) {
				for (Card card : player.offer) {
					if (card.showface() == true) {
						card.facedown();
						this.addToJest(card);
						player.offer.remove(card);
					}
				}
			} else if (upOrDown == 0) {
				for (Card card : player.offer) {
					if (card.showface() == false) {
						this.addToJest(card);
						player.offer.remove(card);
					}
				}
			}
			
			System.out.println("offer is taken.");
			
		} else {
			System.out.println("The offer is incomplete, please choose another one.");
		}
		this.hastakencard = true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Human player ");
		sb.append(this.getname());
	
		return sb.toString();
	}
	
}
