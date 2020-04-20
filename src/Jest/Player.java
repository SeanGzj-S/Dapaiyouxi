
package Jest;
import java.util.*;
/**
 * {@code Player} is a class which represents for players (human players and virtual players).
 */
public class Player {
	/**
	 * The player has a linked list of cards which form the Jest.
	 */
	private LinkedList<Card> jest;
	/**
	 * The player has a linked list of cards which form their offers to other players.
	 */
	protected LinkedList<Card> offer; 
	/**
	 * The final score of the Jest of the player.
	 */
    private int score;
    /**
     * To check whether the offer of the player has been taken or not.
     */
	public boolean hastakencard;
	
	/**
	 * Constructor of the class {@code Player}.
	 * Create the linked list Jest, Offer.
	 * The score is equal to 0 by default.
	 * The offers haven't been taken by default.
	 */
	public Player() {
		jest = new LinkedList<Card>();
		offer = new LinkedList<Card>();
		score=0;
		hastakencard = false;
	}
	/**
	 * To get the value of the attribut {@code hastakencard}
	 * @return type of boolean which shows if the offer of the player has been taken or not.
	 */
	public boolean hastakencard() {
		return hastakencard;
	}
	
	/**
	 * To set the score of the player.
	 * @param s the score to be setted.
	 */
	public void setscore(int s) {
		this.score=s;
	}
	
	/**
	 * To get the score of the player.
	 * @return type of interger, the score of the player.
	 */
	public int getscore() {
		return this.score;
	}
	
	/**
	 * To get the Jest of the player.
	 * @return type of linkedlist, the Jest of the player.
	 */
	public LinkedList<Card> getJest(){
		return this.jest;
	}
	/**
	 * To get the Offer of the player.
	 * @return type of linkedlist, the Offer of the player.
	 */
	public LinkedList<Card> getOffer(){
		return this.offer;
	}
	
	/**
	 * To add a card to the Jest of the player.
	 * @param card the card to be added.
	 */
	public void addToJest(Card card){
		this.jest.add(card);
	}
	
	/**
	 * To check if the offer of the player is complete.
	 * @return type of boolean, the offer of the player is complete or not.
	 */
	public boolean offerIsComplete() {
		return this.offer.size() == 2;
	}
	/**
	 * To get the face-up offer of the player.
	 * @return type of {@code Card}, the face-up offer of the player.
	 */
    public Card getfaceupOffer() {
    	for(Card c : offer) {
    		if (c.showface()) {
				return c;
			}
    	}
    	return null;
    }
	
    
   /**
    * The game table add offers to each players when dealing cards. 
    * @param card, the card to be added.
    */
	public void addOffer(Card card) {
		this.offer.add(card);
	}
	
	/**
	 * Show the cards of the Jest of the players one by one.
	 */
	public void checkJest() {
		for (Card c : this.jest) {
			System.out.println(c.toString());
		}
	}
	
	/**
	 * Check the two cards dealt to the player
	 */
	public void checkOffer() {
		for (Card c : this.offer) {
			System.out.println(c.toString());
		}
	}
	
	/**
	 * Overwrite the function toString() to show the information of the player.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		
		if(this instanceof HumanPlayer) {
			HumanPlayer hm = (HumanPlayer) this;			
			sb.append(hm.getname());
		}
		else {
			VirtualPlayer vt = (VirtualPlayer) this;

			sb.append("VP ");
			sb.append(vt.getnumber());
		}
		
		sb.append(" scores: ");
		sb.append(this.score);
		
		return sb.toString();
	}

	
	/**
	 * To set one of the Offer face-up.
	 * @param card is the offer to face-up
	 */
	public void setFaceUp(Card card) {
		for(Card c : this.offer) {
			if (c.equals(card)) {
				c.faceup();
			}
		}
	}
	
}