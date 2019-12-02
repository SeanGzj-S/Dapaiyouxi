package Jest;
public abstract class Card {
      private boolean face;
      protected String charateristics;
      
      
      public Card() {
    	  this.facedown();
    	  this.setChar();
      }
      public void setChar() {
    	  this.charateristics="undefined";
      }
      
     public String getChar() {
    	 return this.charateristics;
     } 
     
     public void facedown() {
    	 this.face = false;
     } 
     
     public void faceup() {
    	 this.face = true ;
     }
     public boolean showface() {
    	 return this.face;
     }

     public boolean isJoker() {
    	 boolean isjoker=false;
    	 if (this instanceof Joker) {
			isjoker=true;
		}
    	return isjoker; 
     }
  
     
     
     /* 
      * For STRATEGY design pattern
      * To make the jest biggest, we first tend to choose Spade, then Club, then Joker, then Heart, finally Diamonds
      * If the suits of two cards are the same, we tend to choose the one of the bigger value
      * If "this" card is the better choice, return true; else return false
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
    	 
    	 
    	 
     public String toString() {

    	 String string;
     if(this instanceof SuitCard) {
      SuitCard sc =(SuitCard) this;
      string=sc.toString();
     }
     else {
		string="Joker";
	}
     return string;
     }
}
