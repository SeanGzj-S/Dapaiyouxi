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
    	return false; 
     }
  
     public static void main(String[] args) {
    	 Joker joker=new Joker();
    	 joker.setChar();
    	 joker.getChar();

     }
     
     public String toString() {
    	 return null;
     }
     
}
