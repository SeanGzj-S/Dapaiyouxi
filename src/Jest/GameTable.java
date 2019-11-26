package Jest;

import java.util.*;

public class GameTable {
	 private LinkedList<Card> Drawdeck;
	 public  LinkedList<Card> Trophy;
     private LinkedList<Card> Stack;
     public LinkedList<Player> playerlist;
     public int cardnumber;
     private static GameTable gameTable=null;
     
     private GameTable(){
         Drawdeck= new LinkedList<Card>();
         Value[]  v = Value.values();
         Suit[]   s = Suit.values();
         for (int i = 0; i< v.length;i++) {
    	     for(int j = 0; j< s.length; j++) {
    		     Card card = new SuitCard(v[i], s[j]);
    		     Drawdeck.add(card);	   
    	     }
         }
         Drawdeck.add(new Joker());
         Trophy = new LinkedList<Card>();
         Stack  = new LinkedList<Card>();
         cardnumber = Drawdeck.size();
         playerlist = new LinkedList<Player>();
         }
     
     public static GameTable getinstance() {
    	 if(gameTable==null) {
    		 gameTable = new GameTable();
    	 }
    	 return gameTable;
     }
     // singleton
     
     public void addplayer(Player player) {
    	 playerlist.add(player);
     }
     
     public boolean hascard() {
    	 if (Drawdeck.size()!=0) {
			return true;
		}
    	 else {
    		 return false;
    	 }
     }
      
     public boolean enoughplayer() {
    	 return playerlist.size()>=3;
     }

     public void setup() {
    	 if(Trophy.isEmpty()) {
    		Collections.shuffle(Drawdeck);
    		while(Trophy.size()<3) {
    			Trophy.add(Drawdeck.poll());
    		}
    		
    	 }
     }
     
     public void distribute() {
    	 if (Stack.isEmpty()) {
    		 for(int i=0;i<2*playerlist.size();i++) {
    	 			Stack.add(Drawdeck.poll());
    	 			Stack.add(Drawdeck.poll());
    	 			cardnumber -= 2;	
    		 }
		}
    	 else {
    		 for(int i=0;i<playerlist.size();i++) {
 	 			Stack.add(Drawdeck.poll());
 	 			cardnumber -= 1;	
 		 }		 
		}
     }
     // prior condition is that drawdeck is NOT empty. 
     
     public void dealcards() {
    	 Collections.shuffle(Stack);
		 for (Player p : playerlist) {
 			p.addOffer(Stack.poll());
 			p.addOffer(Stack.poll());
		 } 
     }
     
    public Player decideOrder() {
    	LinkedList<Player> list = new LinkedList<Player>();
    	for(int i = 0; i<playerlist.size();i++) {
    		if(playerlist.get(i).offerIsComplete()) {
    			list.add(playerlist.get(i));
    		}	
    	}
    	int index =0;
    	for(int j =1; j < list.size();j++) {
    		if (list.get(index).getfaceupOffer().isJoker()) {
			index=j;	
			}
    		else if (list.get(j).getfaceupOffer().isJoker()) {
			}
    		else {
    			SuitCard ci = (SuitCard) list.get(index).getfaceupOffer();
    			SuitCard cj = (SuitCard) list.get(index).getfaceupOffer();
				if (!ci.compare(cj)) {
					index=j;
				}
    			
			}
    	}
    	
    	return list.get(index);
    }
     
     
 	public void recycle() {
		for(Player player: playerlist) {
			Stack.add(player.offer.poll());//*maybe an error 
		}
	}
 	// default is that every player has taken a card from the offers
}
