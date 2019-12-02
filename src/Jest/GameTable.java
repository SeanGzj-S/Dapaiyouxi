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
    		if (playerlist.size()==3) {
        		while(Trophy.size()<2) {
        			Trophy.add(Drawdeck.poll());
        			cardnumber-=1;
        		}
			}
    		else if (playerlist.size()==4) {
    			Trophy.add(Drawdeck.poll());
    			cardnumber-=1;
			}
    	 }
     }
     
     public void distribute() {
    	 if (Stack.isEmpty()) {
    		 for(int i=0;i<playerlist.size();i++) {
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
			for (Card c : player.offer) {
				if (c.showface()) {
					c.facedown();
				}
			} 
			Stack.add(player.offer.poll());
		}
	}
 	
 	public void accept(Visitor visitor) {
 		
 		
 		visitor.calculatescores(playerlist);
 	}
 	
 	
 	public Player matchchar(String charateristics) {
 		Player rp =new Player(); 	
 		
 		if(charateristics == "Joker") {
 			for (Player player : playerlist) {
 				for(Card card : player.getJest()) {
 					if (card instanceof Joker) {
 						rp = player;
					}
 				}	
 			}
 		}
 		
 		else if (charateristics=="Best Jest") {
 			rp =  this.decidewinner(playerlist);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(indexp);
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
			rp =  this.decidewinner(playerlisttocal);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(index);
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
			rp =  playerlist.get(index);
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
			rp =  playerlist.get(index);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(indexp);
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
			rp =  playerlist.get(indexp);
		}
 		return rp;// why can't I delete this sentence?
 		
 	}
 	
 	
 	
 	public void assignTrophy() {
 		for (Card c : Trophy) {
 			matchchar(c.getChar()).addToJest(c);
 		}
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
 	
 	
 	public static void main(String[] args) {
 		GameTable gameTable = getinstance();
 		Scanner scan = new Scanner(System.in);
       
        int i = 0;   // number of human players
        String j;   // type of virtual player
        int m = 0;   // the number of a virtual player
        System.out.print("How many players are you：");
        
       
        while(scan.hasNextLine()&&(i<1||i>4)) {
            if (scan.hasNextInt()) {

                i = scan.nextInt();
                // 接收整数
                if (i<5&i>0) {
                	 System.out.println("You have decided " + i+" players");
    			}
                else {
                	System.out.println("You must decide the number of players in the range of 1 to 4! Try again!");
                }
            } 
        }


        
        while(gameTable.playerlist.size()!=i) {
        	
        	System.out.println("Please write your name:");

        	String name = scan.next();
        	gameTable.playerlist.add(new HumanPlayer(name));
        }


        
        
          if (i<4) {
              System.out.println("Please chose a number as the type of virtual player:1 Easy,2 Normal,3 Tough");
        	  while(scan.hasNextLine()&&gameTable.playerlist.size()<3) {
              	


                  	j=scan.nextLine();     


                      if (j.contentEquals("1")) {
                    	  m++;
                      	gameTable.playerlist.add(new VirtualPlayer(m, new Easy()));
                      	System.out.println("An easy player is created");
                      	
                      	if (gameTable.playerlist.size()<3) {
							System.out.println("another one");
						}
                      }
                      else if (j.contentEquals("2")) {
                    	  m++;
                      	gameTable.playerlist.add(new VirtualPlayer(m, new Normal()));
                      	System.out.println("A normal player is created ");
                      	
                      	if (gameTable.playerlist.size()<3) {
							System.out.println("another one");
						}
      				}
                      else if (j.contentEquals("3")) {
                    	  m++;
                      	gameTable.playerlist.add(new VirtualPlayer(m, new Tough()));
                      	
                      	if (gameTable.playerlist.size()<3) {
							System.out.println("another one");
						}
      				}
                  
                      
              
       	}
		}
        
       


        for (Player player : gameTable.playerlist) {
    		if(player instanceof HumanPlayer) {
    			HumanPlayer hm = (HumanPlayer) player;
    			System.out.println(hm.getname());
    		}
    		else {
    			VirtualPlayer vt = (VirtualPlayer) player;

    			System.out.println(vt.getnumber());
    		}
		}
        gameTable.setup();
        while (gameTable.hascard() == true) {

        	gameTable.distribute();
        	gameTable.dealcards();
        	
        	
        	for(int hp =0;hp<i;hp++) {
        		gameTable.playerlist.get(hp).checkOffer();
        		System.out.println(" ");
        	}
        	
      
        	
        	 for(Player p: gameTable.playerlist) {

            		if (p instanceof HumanPlayer) {
            			HumanPlayer human = (HumanPlayer) p;
            			boolean offermade = false;
            			while (!offermade) {
    					System.out.println("make ur offer decide the first or the second to face up(choose by number 1 or 2)");
    					
        					human.makeOffer(human.getOffer().get(scan.nextInt()-1));
        					offermade=true;
						}
            		}
            		else if(p instanceof VirtualPlayer) {
						VirtualPlayer robot = (VirtualPlayer) p;
						robot.StrategyOffer();
					}		
            	}		//make all the offers		
			
           for(int np = 0 ; np<gameTable.playerlist.size();np++) {
        	   for(int nc = 1; nc<3;nc++) {
        		   if(gameTable.playerlist.get(np).getOffer().get(nc-1).showface()) {
        			   System.out.print("player "+np+"("+gameTable.playerlist.get(np).toString()+")"+" has a faceup card : ");
        			   System.out.println(gameTable.playerlist.get(np).getOffer().get(nc-1).toString()+" which is the number " + nc + " in his/her offer ");
        		   }
        	   }
        	   System.out.println(gameTable.playerlist.get(np).getOffer().size());
           }
        	//show all the face up card and related info


           Player playerdecided = null;
	
           while(gameTable.fullofferPlayers().size()!=0) {
               
        	   if (playerdecided==null||playerdecided.hastakencard) {
            	   playerdecided = gameTable.decideOrder();
			}
        	   
       		if (playerdecided instanceof HumanPlayer) {
        			HumanPlayer human = (HumanPlayer) playerdecided;

   					    System.out.println("take card,please choose by the code of player and the card(0 for facedown, 1 for faceup):");
   					    int tnp =scan.nextInt();
   					    if (tnp<gameTable.playerlist.size()) {
   	   					 
                            System.out.println(gameTable.playerlist.get(tnp).toString());
           					human.takeOffer(gameTable.playerlist.get(tnp),scan.nextInt());
						}
 
	
   					    if(human.hastakencard) {
   					    	playerdecided=gameTable.playerlist.get(tnp);
   					    }

        		}
       		
        		else if(playerdecided instanceof VirtualPlayer) {
   					VirtualPlayer robot = (VirtualPlayer) playerdecided;
   					LinkedList<Player> offeredPlayers = gameTable.fullofferPlayers();
   					playerdecided=robot.StrategyTake(offeredPlayers);
   				}
           
           
           }
           //take offer one by one
           
         	 if (gameTable.hascard()) {
				gameTable.recycle();
			}
        	
		}
        
        //n-1 rounds
        for(Player p:gameTable.playerlist) {
        	
        	p.addToJest(p.getOffer().poll());
        }
        // take card from their own offer
        
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        
        gameTable.accept(scoreCalculator);
        
        gameTable.assignTrophy();
        
        gameTable.accept(scoreCalculator);
        
        
        for(Player p:gameTable.playerlist) {
        	System.out.print(p.toString());
        	System.out.println(" has a set of jest as below: ");
        	for (Card c : p.getJest()) {
				System.out.println(c.toString());
			}
        	
        	System.out.println(p.getscore());
        }
        
        
        
        System.out.println("winner is:" );
        
        System.out.println(gameTable.decidewinner(gameTable.playerlist).toString());
        
        
        
        
 }
}





// problem left: player.tostring
//exceptions

