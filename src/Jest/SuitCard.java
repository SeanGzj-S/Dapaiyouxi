package Jest;
/**
 * {@code SuitCard} is a kind of card so it extends the class {@code Card}.
 */
public class SuitCard extends Card {
    /**
     * The suit card has a suit.
     */
	private Suit suit;
	/**
	 * The suit card has a value.
	 */
	private Value value;
	
	/**
	 * Constructor of the class {@code SuitCard}.
	 * @param value is the value {@code Value} to set.
	 * @param suit is the suit {@code Suit} to set.
	 */
	public SuitCard(Value value, Suit suit) {
		super();
		this.setSuit(suit);
		this.setValue(value);
		this.setChar();
	}
	
	/**
	 * To set the value of the suit card.
	 * @param value is the value to set.
	 */ 
	public void setValue(Value value) {
		this.value = value;
	}
	/**
	 * To set the suit of the suit card.
	 * @param suit is the suit to set.
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	/**
	 * To get the value of the suit card.
	 * @return type of a {@code Value} is the value to get.
	 */
	public Value getValue() {
		return this.value;
	}
	/**
	 * To get the suit of the suit card.
	 * @return type of a {@code Suit} is the suit to get.
	 */
	public Suit getSuit() {
		return this.suit;
	}
	/**
	 * To set the characteristics of the suit card according to the combination of its suit and value.
	 */
	public void setChar() {
		if(this.suit==Suit.Spade&&this.value==Value.four) {
			this.charateristics="Lowest Club";
			}
		else if (this.suit==Suit.Club&&this.value==Value.four) {
			this.charateristics="Lowest Spade";
		}
		else if (this.suit==Suit.Diamond&&this.value==Value.four) {
			this.charateristics="Best Jest,No Joke";
		}
		else if (this.suit==Suit.Spade&&this.value==Value.Ace) {
			this.charateristics="Highest Club";
		}
		else if (this.suit==Suit.Club&&this.value==Value.Ace) {
			this.charateristics="Highest Spade";
		}
		else if (this.suit==Suit.Diamond&&this.value==Value.Ace) {
			this.charateristics="Majority four";
		}
		else if (this.suit==Suit.Spade&&this.value==Value.two) {
			this.charateristics="Majority three";
		}
		else if (this.suit==Suit.Club&&this.value==Value.two) {
			this.charateristics="Lowest Heart";
		}
		else if (this.suit==Suit.Diamond&&this.value==Value.two) {
			this.charateristics="Highest Diamond";
		}
		else if (this.suit==Suit.Spade&&this.value==Value.three) {
			this.charateristics="Majority two";
		}
		else if (this.suit==Suit.Club&&this.value==Value.three) {
			this.charateristics="Highest Heart";
		}
		else if (this.suit==Suit.Diamond&&this.value==Value.three) {
			this.charateristics="Lowest Diamond";
		}
		else {
			this.charateristics="Joker";
		}
		
	}
	/**
	 * To compare two suit cards.
	 * First compare the value of two cards, the card whose value is bigger is the bigger one.
	 * If both cards have the same value, then compare their suits.
	 * Spades is the strongest suit, then Clubs, Diamonds and Hearts, the same order in the Enum {@code Suit}.
	 * @param card the card to compared.
	 * @return type of boolean to show if the instance of the card is bigger or not.
	 */
	public boolean compare(SuitCard card) {
		if (this.value.ordinal()>card.value.ordinal()) {
			return true;
		}
		else if (this.value.ordinal()<card.value.ordinal()) {
			return false;
		}
		else {
			if (this.suit.ordinal()>card.suit.ordinal()) {
				return true;
			}
			else {
				return false;
			}	
		}
	}
	
	/**
	 * Overwrite the function toString() to show the information of a suit card {@code SuitCard}.
	 */
	public String toString(){
		StringBuffer bf = new StringBuffer();
		bf.append("[");
		bf.append(this.suit);
		bf.append(" ");
		bf.append(this.value);
		bf.append("]");
		return bf.toString();
	}
	
	
	
}
