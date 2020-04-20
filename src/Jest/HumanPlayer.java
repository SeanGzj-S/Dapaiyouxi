package Jest;

/**
 * {@code HumanPlayer} is a class which represents for human players.
 * As human player is a player, it extends the class {@code Player}.
 */
public class HumanPlayer extends Player {
	 /**
	  * Attribut {@code name} is the name for the human player.
	  */
	private String name;
	
	/**
	 * {@code HumanPlayer(String name)} is the constructor of the class {@code HumanPlayer}.
	 * @param name is the name of the human player.
	 */
    public HumanPlayer(String name) {
	       super();
	       this.name=name;	
    }	
    /**
     * To get the name of the human player.
     * @return type of String, the name of the human player.
     */
    public String getname() {
    	return this.name;
    }
    
    /**
     * The human player faces up a card to make offers
     * @param card is the card which the player want to face up.
     */
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
	
	/**
	 * The human player takes a face-up or face-down offer from another player.
	 * @param player is the player from whom the human player wants to take an offer from.
	 * @param upOrDown is the face status of the card which the human player wants to take.
	 */
	public void takeOffer(Player player, int upOrDown) {
		if (player.offerIsComplete()&&(upOrDown==0||upOrDown==1)) {
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
			this.hastakencard = true;
			System.out.println("offer is taken.");
			
		} else {
			System.out.println("The offer is incomplete or the number for card is mistaken, please choose another one.");
		}

	}
	
	/**
	 * Overwrite the function toString() to show the information of the huamn player.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Human player ");
		sb.append(this.getname());
	
		return sb.toString();
	}
	
}
