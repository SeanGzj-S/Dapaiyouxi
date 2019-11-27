package Jest;

public class SuitCard extends Card {
    
	private Suit suit;
	private Value value;
	
	
	public SuitCard(Value value, Suit suit) {
		super();
		this.setSuit(suit);
		this.setValue(value);
		this.setChar();
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public Value getValue() {
		return this.value;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
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
	
	public String toString(){
		StringBuffer bf = new StringBuffer();
		bf.append("[");
		bf.append(this.suit);
		bf.append(" ");
		bf.append(this.value);
		bf.append("] ");
		return bf.toString();
	}
	
	
	
}
