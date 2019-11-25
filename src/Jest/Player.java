
package Jest;
import java.util.*;

public class Player {
	private LinkedList<Card> jest;
	private LinkedList<Card> offer; //your offers to other players
	private int number;
	
	public Player(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public LinkedList<Card> getJest(){
		return this.jest;
	}
	
	public LinkedList<Card> getOffer(){
		return this.offer;
	}
	
	public void addToJest(Card card){
		this.jest.add(card);
	}
	
	//check if the offer of the player is complete
	public boolean offerIsComplete() {
		return this.offer.size() == 2;
	}
    public Card getfaceupOffer() {
    	for(Card c : offer) {
    		if (c.showface()) {
				return c;
			}
    	}
    	return null;
    }
	
    
    
	//The gametable add offers to each players when dealing cards.
	public void addOffer(Card card) {
		this.offer.add(card);
	}
	
	
	public void checkJest() {
		for (Card c : this.jest) {
			System.out.println(c);
		}
	}
	
	//check the two cards dealt to you
	public void checkOffer() {
		for (Card c : this.offer) {
			System.out.println(c);
		}
	}
	
	//make one of the offers faced-up
	public void makeOffer(Card card) {
		if (this.offer.contains(card)) {
			card.faceup();
		} else {
			System.out.println("You don't have this card in your offer, please choose again.\n");
		}
	}
	
	//choose a player and take a card (1=the faced-up one, 0=the faced-down one)
	public void takeOffer(Player player, int upOrDown) {
		if (!offerIsComplete()) {
			System.out.println("The offer is incomplete, please choose another one.");
		} else {
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
		}
	}
	

}