package Jest;

/**
 * The class {@code Card} represents all the cards used for playing (suit cards and Joker).
 * It is an abstract class.
 */

public abstract class Card {
	  /**
	   * The attribut face shows the face status of the card (true = faced-up and false = faced-down)
	   */
      private boolean face;
      /**
       * The attribut charateristics shows the description of the card.
       */
      protected String charateristics;
      
      /**
       * {@code Card()} is the constructor of the class {@code Card}.
       * There is no parameters.
       * To create a card by default, the card is faced down and its characteristic is undefined.
       */
      public Card() {
    	  this.face = false;
    	  this.charateristics="undefined";
      }
      
      /**
       * {@code getChar()} allows to get the characteristic of the card from outside. 
       * @return type of a String, which is the characteristic of the card.
       */
     public String getChar() {
    	 return this.charateristics;
     } 
     
     /**
      * {@code facedown()} allows to face down a card by setting its face status to false.
      */
     public void facedown() {
    	 this.face = false;
     } 
     
     /**
      * {@code faceup()} allows to face up a card by setting its face status to true.
      */
     public void faceup() {
    	 this.face = true ;
     }
     
     /**
      * {@code showface()} allows to get the face status of the card.
      * @return type of a boolean to show whether the card is faced up(true) or faced down(false).
      */
     public boolean showface() {
    	 return this.face;
     }
     
     /**
      * {@code cardEquals(Card card)} allows to compare if the present card is same to a card sent in as parameter.
      * If the two cards are the same, they have the same type. If they are both {@code SuitCard}, they have the same suits and the same values.
      * @param card is a instance of {@code Card} which is to be compared with the instance who call the function.
      * @return type of a boolean to show if the two cards are the same.
      */
     public boolean cardEquals(Card card) {
    	 if (this instanceof Joker && card instanceof Joker) {
    		 return true;
    	 } else if (this instanceof SuitCard && card instanceof SuitCard) {
    		 SuitCard card1 = (SuitCard)this;
    		 SuitCard card2 = (SuitCard)card;
    		 if (card1.equals(card2)) {
    			 return true;
    		 } else {return false;}
    	 } else {
    		 return false;
    	 }
     }
     
     
     /**
      * {@code isJoker()} is to check if the card is of the type Joker {@code Joker}
      * @return type of a boolean to show the result. True means is a Joker and false means is not.
      */
     public boolean isJoker() {
    	 boolean isjoker=false;
    	 if (this instanceof Joker) {
			isjoker=true;
		}
    	return isjoker; 
     }
     
     /**
      * Overwrite the function {@code toString()} to display the type and characteristics of the card.
      */
     public String toString() {
    	 String string;
    	 if(this instanceof SuitCard) {
    		 SuitCard sc =(SuitCard) this;
    		 string=sc.toString();
    		 } else {
    			 string="Joker";
    			 }
    	 return string;
    	 }
     
     /**
      * {@code comCard(Card card)} only compares value and suit when deciding the order of taking offers
      * @param card is a instance of {@code Card} which is to be compared with the instance who call the function.
      * @return type of a boolean to show if the card is the better one.
      */
     public boolean comCard(Card card) {
    	 if (this instanceof Joker) {
    		 return false;
    	 } else if (card instanceof Joker) {
    		 return true;
    	 } else {
    		 SuitCard s1 = (SuitCard)this;
    		 SuitCard s2 = (SuitCard)card;
    		 return s1.compare(s2);
    	 }
    	 
     }
     
     /** 
      * {@code compareCard(Card card)} is for STRATEGY design pattern.
      * To make the jest biggest, we first tend to choose Spade, then Club, then Joker, then Heart, finally Diamonds
      * If the suits of two cards are the same, we tend to choose the one of the bigger value
      * If "this" card is the better choice, return true; else return false
      * @param card is a instance of {@code Card} which is to be compared with the instance who call the function.
      * @return type of a boolean to show if the card is the better one.
      */
     public boolean compareCard(Card card) {
    	 if (this instanceof Joker) {
    		 SuitCard card2 = (SuitCard)card;
    		 if (card2.getSuit() == Suit.Spade || card2.getSuit() == Suit.Club) {
    			 return false;
    		 } else {return true;}
    	 }
    	 else if (card instanceof Joker) {
    		 SuitCard card2 = (SuitCard)this;
    		 if (card2.getSuit() == Suit.Spade || card2.getSuit() == Suit.Club) {
    			 return true;
    		 } else {return false;}
    	 }
    	 else {
    		 SuitCard card1 = (SuitCard)this;
    		 SuitCard card2 = (SuitCard)card;
    		 if (card1.getSuit() == card2.getSuit()) {
    			 return card1.compare(card2);
    		 }
    		 else if (card1.getSuit() == Suit.Spade) {return true;}
    		 else if (card1.getSuit() == Suit.Club) {
    			 if (card2.getSuit() == Suit.Spade) {return false;}
    			 else {return true;}
    		 }
    		 else if (card1.getSuit() == Suit.Heart) {
    			 if (card2.getSuit() == Suit.Spade || card2.getSuit() == Suit.Club) {return false;}
    			 else {return true;}
    		 }
    		 else {return false;}
    		 }
     }
     
}