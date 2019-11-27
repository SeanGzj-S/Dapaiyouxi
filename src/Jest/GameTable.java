package Jest;

import java.util.*;

public class GameTable implements Accepter{
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
     
     
     public LinkedList<Player> fullofferPlayers(){
     	LinkedList<Player> list = new LinkedList<Player>();
     	for(int i = 0; i<playerlist.size();i++) {
     		if(playerlist.get(i).offerIsComplete()) {
     			list.add(playerlist.get(i));
     		}	
     	}
    	 
    	 return list;
     }
     
     
     
    public Player decideOrder() {
    	LinkedList<Player> list = this.fullofferPlayers();
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
 	
 	public void accept(Visitor visitor) {
 		
 		
 		visitor.calculatescores(playerlist);
 	}
 	
 	
 	public Player matchchar(String charateristics) {
 		
 		if(charateristics == "Joker") {
 			for (Player player : playerlist) {
 				for(Card card : player.getJest()) {
 					if (card instanceof Joker) {
						return player;
					}
 				}	
 			}
 		}
 		
 		else if (charateristics=="Best Jest") {
 			return this.decidewinner(playerlist);
		}
 		
 		else if (charateristics=="Lowest Club") {
			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Club) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()>c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}	
				}
			}
			return playerlist.get(indexp);
		}
 		
 		else if (charateristics=="Lowest Spade") {
 			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Spade) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()>c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}
					
				}
			}
			return playerlist.get(indexp);
		}
 		
 		else if (charateristics=="Best Jest,No Joke") {
			LinkedList<Player> playerlisttocal = new LinkedList<Player>();
			for(Player player : playerlist) {
				boolean hasjoker = false;
				for(Card c : player.getJest()) {
					if (c instanceof Joker) {
						hasjoker = true;
					}	
				}
				if (!hasjoker) {
					playerlisttocal.add(player);
				}
			}
			return this.decidewinner(playerlisttocal);
		}
 		
 		else if (charateristics=="Highest Club") {
			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Club) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()<c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}
					
				}
			}
			return playerlist.get(indexp);
		}
 		
 		else if (charateristics=="Highest Spade") {
			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Spade) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()<c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}
					
				}
			}
			return playerlist.get(indexp);
		}
 		
 		else if (charateristics=="Majority four") {
			int[] counter= {0,0,0,0};
			int index = 0;
			int mark = 0;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getValue()==Value.four) {
							counter[i]+=1;
							if (c1.getSuit()==Suit.Spade) {
								mark=i;
							}
						}
				  }
			  }
			}
			
			for(int m = 1 ; m<4; m++) {
				if (counter[index]<counter[m]) {
					index=m;
				}
				else if (counter[index]==counter[m]||counter[m]==2) {
					return playerlist.get(mark);
				}
			}
			return playerlist.get(index);
		}
 		
 		else if (charateristics=="Majority three") {
			int[] counter= {0,0,0,0};
			int index = 0;
			int mark = 0;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getValue()==Value.three) {
							counter[i]+=1;
							if (c1.getSuit()==Suit.Spade) {
								mark=i;
							}
						}
				  }
			  }
			}
			
			for(int m = 1 ; m<4; m++) {
				if (counter[index]<counter[m]) {
					index=m;
				}
				else if (counter[index]==counter[m]||counter[m]==2) {
					return playerlist.get(mark);
				}
			}
			return playerlist.get(index);
		}
 		
 		else if (charateristics=="Majority two") {
			int[] counter= {0,0,0,0};
			int index = 0;
			int mark = 0;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getValue()==Value.two) {
							counter[i]+=1;
							if (c1.getSuit()==Suit.Spade) {
								mark=i;
							}
						}
				  }
			  }
			}
			
			for(int m = 1 ; m<4; m++) {
				if (counter[index]<counter[m]) {
					index=m;
				}
				else if (counter[index]==counter[m]||counter[m]==2) {
					return playerlist.get(mark);
				}
			}
			return playerlist.get(index);
		}
 		
 		else if (charateristics=="Lowest Heart") {
 			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Heart) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()>c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}	
				}
			}
			return playerlist.get(indexp);
		}
 		
 		else if (charateristics=="Highest Diamond") {
			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Diamond) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()<c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
								
							}
						}
					}
					
				}
			}
			return playerlist.get(indexp);
		}
		
 		
 		else if (charateristics=="Highest Heart") {
 			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Heart) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()<c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}
							}
						}
					}
				}
			}
			return playerlist.get(indexp);
 		 }
		
 		
 		else if (charateristics=="Lowest Diamond") {
			int indexp = -1;
			int indexc = -1;
			for (int i =0;i<playerlist.size();i++) {
				for(int j = 0;j<playerlist.get(i).getJest().size();j++) {
					if (playerlist.get(i).getJest().get(j) instanceof SuitCard) {
						SuitCard c1 = (SuitCard) playerlist.get(i).getJest().get(j);
						if (c1.getSuit()==Suit.Diamond) {
							if (indexc==-1||indexp==-1) {
								indexp=i;
								indexc=j;
							}
							else{
								SuitCard c = (SuitCard) playerlist.get(indexp).getJest().get(indexc);
								if (c.getValue().ordinal()>c1.getValue().ordinal()) {
									indexp=i;
									indexc=j;
								}	
							}
						}
					}
				}
			}
			return playerlist.get(indexp);
		}
 		return null;
 	}
 	
 	
 	
 	public void assignTrophy() {
 		for (Card c : Trophy) {
 			matchchar(c.getChar()).addToJest(c);
 		};
 	}
 	
 	public Player decidewinner(LinkedList<Player> playerlisttocal) {
 		
 		int index = 0;
 		
 		for(int i = 1 ; i<playerlisttocal.size();i++) {
 			if (playerlisttocal.get(index).getscore()<playerlisttocal.get(i).getscore()) {
				index=i;
			}
 		}
 		
 		return playerlisttocal.get(index);
 	}
 	
 	// default is that every player has taken a card from the offers
}
