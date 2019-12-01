
package Jest;
import java.util.*;

public class Player {
	private LinkedList<Card> jest;
	protected LinkedList<Card> offer; //your offers to other players
    private int score;
	protected boolean hastakencard;
	
	public Player() {
		jest = new LinkedList<Card>();
		offer = new LinkedList<Card>();
		score=0;
		hastakencard = false;
	}
	
	public void setscore(int s) {
		this.score=s;
	}

	public int getscore() {
		return this.score;
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
			System.out.println(c.toString());
		}
	}
	
	//check the two cards dealt to you
	public void checkOffer() {
		for (Card c : this.offer) {
			System.out.println(c.toString());
		}
	}
	

	public String toString() {
		StringBuffer sb = new StringBuffer();

		
		if(this instanceof HumanPlayer) {
			HumanPlayer hm = (HumanPlayer) this;
			sb.append(hm.toString());
		}
		else {
			VirtualPlayer vt = (VirtualPlayer) this;

			sb.append(vt.toString());
		}
		
		sb.append(" scores: ");
		sb.append(this.score);
		
		return sb.toString();
	}

}