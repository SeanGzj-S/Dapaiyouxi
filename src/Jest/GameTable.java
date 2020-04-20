package Jest;

import java.util.*;
import Vue.*;
import Vue.Observer;

/**
 * The class {@code GameTable} represents the game table which contains all the cards,
 * all the players and all the operations of the game system.
 * There is the main function in this class. 
 * Because of concurrent programming, it should be a thread
 * which enables the player to play the game from command line.
 * Therefore, it implements the class {@code Runnable}.
 */
public class GameTable extends Observable implements Accepter,Runnable {
	/**
	 * {@code Drawdeck} is a linked list of cards which formed the drawdeck.
	 */
	 private LinkedList<Card> Drawdeck;
	 /**
      * {@code Trophy} is a linked list of cards which formed the trophy.
      * Trophy is the card which will be added to the jest of the players whose Jest meets the condition in the orange band of each Trophy.
	  */
	 public  LinkedList<Card> Trophy;
	 /**
	  * {@code Stack} is a linked list of cards which formed the Stack.
	  */
     private LinkedList<Card> Stack;
     /**
      * {@code playerlist} is a linked list of players who play the game (human players and virtual players)
      */
     public LinkedList<Player> playerlist;
     /**
      * {@code playerlist} is a linked list of human players who play the game.
      */
     public LinkedList<HumanPlayer> humanPlayers;
     /**
      * {@code playerlist} is a linked list of virtual players who play the game.
      */
     public LinkedList<VirtualPlayer> virtualPlayers;
     /**
      * 
      */
     public int cardnumber;
     /**
      * For the design pattern Singleton, we first create the unique instance of the class {@code GameTable}.
      * We now just declare it but not create it.
      */
     private static GameTable gameTable=null;
     /**
      * For the input from the keyboard.
      */
	 private static	Scanner scan = new Scanner(System.in);
	 /**
	  * 
	  */
	 public InterfaceOffer InterfaceOffer;
	 /**
	  * 
	  */
	 public InterfaceOffer interfaceOffer2;
	 /**
	  * {@code playerdicided} is the player who is going to take the offer.
	  */
	 public Player playerdecided;
	 /**
	  * {@code consoleThread} is for concurrent programming. 
	  * This is the thread which enables the player to play the game from command line.
	  */
	 private Thread consoleThread;
	 
	 /**
	  * {@code GameTable()} is the constructor of the class {@code GameTable}.
	  * Because of the use of the design pattern Singleton, here we make the constructor private.
	  * Here we create a new {@code Drawdeck} and add all the suitcards (with all the values and suits) and the Joker into it.
	  * We create a new {@code Trophy},{@code Stack},{@code humanPlayers},{@code virtualPlayers}.
	  * The number of the cards is equal to the size of the drawdeck.
	  * We define a thread {@code consoleThread}.
	  */
     private GameTable(){
    	 super();
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
         InterfaceOffer=new InterfaceOffer();
         interfaceOffer2=new InterfaceOffer();
         humanPlayers=new LinkedList<HumanPlayer>();
         virtualPlayers=new LinkedList<VirtualPlayer>();
         playerdecided=null;
         consoleThread=new Thread(this);
         
         }
     /**
      * Because of the use of the design pattern Singleton, {@code getinstance()} returns the unique instance of the class {@code GameTable}.
      * If the instance is not defined, we create a new one and send it to the unique instance which we have declared as attribut.
      * @return type of {@code GameTable} which is the unique instance of the class.
      */
     public static GameTable getinstance() {
    	 if(gameTable==null) {
    		 gameTable = new GameTable();
    	 }
    	 return gameTable;
     }
     
     /**
      * {@code addplayer(Player player)} enables to add a new player to the playerlist.
      * @param player is the player which will be added to the playerlist.
      */
     public void addplayer(Player player) {
    	 playerlist.add(player);
     }
     
     /**
      * {@code hascard()} can check if there are still cards in the drawdeck.
      * @return type of boolean (true = yes, false = no).
      */
     public boolean hascard() {
    	 return Drawdeck.size()!=0;
     }
     
     /**
      * {@code enoughplayer()} can check if there are enough players for the game.
      * We here only accept from 1 player to 4 players.
      * @return type of boolean (true = yes, false = no).
      */
     public boolean enoughplayer() {
    	 return playerlist.size()>=3;
     }
     
     /**
      * {@code setup()} can set up the game table to start the game.
      * If there are 3 players there will be 2 trophy.
      * If there are 4 players there will be 1 trophy.
      * Here shuffle the drawdeck and choose the certain number of them randomly as trophy.
      */
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
     
     /**
      * {@code distribute()} adds to the stack a number of cards from the draw deck equal to the number of players.
      */
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
     
     /**
      * {@code dealcards()} deals each player 2 cards face-down from the drew deck.
      */
     
     public void dealcards() {
    	 Collections.shuffle(Stack);
		 for (Player p : playerlist) {
 			p.addOffer(Stack.poll());
 			p.addOffer(Stack.poll());
		 } 
		 playerdecided=null;
     }
     
     
    /**
     * {@code fullofferPlayers()} checks the player in the player list one by one if their offers have been taken of not.
     * @return a linked list of players whose offers have not been taken.
     */
     public LinkedList<Player> fullofferPlayers(){
     	LinkedList<Player> list = new LinkedList<Player>();
     	for(int i = 0; i<playerlist.size();i++) {
     		if(playerlist.get(i).offerIsComplete()) {
     			list.add(playerlist.get(i));
     		}	
     	}
    	 
    	 return list;
     }
 
    /**
     * {@code decideOrder()} decides the which player will be the next to take offer.
     * @return type of class {@code Player} which is the player who will be the next to take offer.
     */
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
    			SuitCard cj = (SuitCard) list.get(j).getfaceupOffer();
				if (!ci.compare(cj)) {
					index=j;
				}
    			
			}
    	}
    	
    	return list.get(index);
    }
     
    /**
     * At the end of every round, {@code recycle()} recycles all the offers remained on the table and adds them into the stack.
     */
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
 	
 	/**
 	 * Design pattern Visitor.
 	 * To accept the visit of {@code Visitor}.
 	 */
 	public void accept(Visitor visitor) {
 		
 		
 		visitor.calculatescores(playerlist);
 	}
 	
 	/**
 	 * At the end of the game, compare everyone's Jests to discover whose Jest meets the condition in the orange band of each trophy.
 	 * @param charateristics is the description of the condition in the orange band of each trophy.
 	 * @return type of {@code Player} who is the player whose Jest meets the condition in the orange band of the trophy with corresponding characteristics.
 	 */
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
 	
 	
 	/**
 	 * For each card in the Trophy, find out the player who will gain it with the function {@code matchchar(String charateristics)}.
 	 */
 	public void assignTrophy() {
 		for (Card c : Trophy) {
 			matchchar(c.getChar()).addToJest(c);
 		}
 	}
 	
 	/**
 	 * To decide the winner.
 	 * @param playerlisttocal is the list of all the players whose Jests will be compared.
 	 * @return type of {@code Player} is the winner of the game.
 	 */
 	public Player decidewinner(LinkedList<Player> playerlisttocal) {
 		
 		int index = 0;
 		
 		for(int i = 1 ; i<playerlisttocal.size();i++) {
 			if (playerlisttocal.get(index).getscore()<playerlisttocal.get(i).getscore()) {
				index=i;
			}
 		}
 		
 		return playerlisttocal.get(index);
 	}
 	
 	/**
 	 * {@code createVirtualPlayers(int i,int m)} creates corresponding number of virtual players.
 	 * @param i is the number of human players.
 	 * @param m is the number of virtual players.
 	 */
 	public void createVirtualPlayers(int i,int m) {
 		String j;
 		
 		if (i < 4) {
			System.out.println("Please choose a number as the type of virtual player:1 Easy,2 Normal,3 Tough");
			while (scan.hasNextLine() && gameTable.playerlist.size() < 3) {

				j = scan.nextLine();

				if (j.contentEquals("1")) {
					m++;
					gameTable.playerlist.add(new VirtualPlayer(m, new Easy()));
					System.out.println("An easy player is created");

					if (gameTable.playerlist.size() < 3) {
						System.out.println("Another one");
					}
				} else if (j.contentEquals("2")) {
					m++;
					gameTable.playerlist.add(new VirtualPlayer(m, new Normal()));
					System.out.println("A normal player is created ");

					if (gameTable.playerlist.size() < 3) {
						System.out.println("Another one");
					}
				} else if (j.contentEquals("3")) {
					m++;
					gameTable.playerlist.add(new VirtualPlayer(m, new Tough()));
					System.out.println("A tough player is created");

					if (gameTable.playerlist.size() < 3) {
						System.out.println("Another one");
					}
				}

			}
		}
 	}
 	
 	/**
 	 * {@code createvirtualplayer(int m,Level level)} creates a virtual player.
 	 * @param m is the serial number of this virtual player.
 	 * @param level is the level of this virtual player (easy,normal,tough).
 	 */
 	public void createvirtualplayer(int m,Level level) {
 		VirtualPlayer virtualPlayer = new VirtualPlayer(m, level);
 		gameTable.virtualPlayers.add(virtualPlayer);
 		gameTable.playerlist.add(virtualPlayer);
 	}
 	
 	/**
 	 * {@code createhumanplayer(String name)} creates a human player and adds it to the list of human players
 	 * @param name is the name of the human player.
 	 */
 	public void createhumanplayer(String name) {
 	    HumanPlayer humanPlayer =new HumanPlayer(name);
 		gameTable.humanPlayers.add(humanPlayer);
		gameTable.playerlist.add(humanPlayer);
 	}
 	
 	/**
 	 * {@code showPlayers()} shows all the players (human players and virtual players)
 	 */
 	public void showPlayers() {
 		for (Player player : gameTable.playerlist) {
			if (player instanceof HumanPlayer) {
				HumanPlayer hm = (HumanPlayer) player;
				System.out.println(hm.getname());
			} else {
				VirtualPlayer vt = (VirtualPlayer) player;

				System.out.println(vt.getnumber());
			}
		}
 	}
 	
 	/**
 	 * For all the human players, shows their Jests and two cards dealt to them, and then the human player choose one to face up.
 	 * For all the virtual players, call the function {@code StrategyOffer()}.
 	 * @param i
 	 */
 	public void makeOffer(int i) {

			for (int hp = 0; hp < i; hp++) {
				System.out.println("The offer of " + gameTable.playerlist.get(hp)+":");
				gameTable.playerlist.get(hp).checkOffer();
				System.out.println(" ");
			}

			for (Player p : gameTable.playerlist) {

				if (p instanceof HumanPlayer) {
					HumanPlayer human = (HumanPlayer) p;
					boolean offermade = false;
					while (!offermade) {
						System.out.println(p+":");
						System.out.println(
								"Make your offer : decide the first or the second to face up(choose by number 1 or 2)");

						human.makeOffer(human.getOffer().get(scan.nextInt() - 1));
						offermade = true;
					}
				} else if (p instanceof VirtualPlayer) {
					VirtualPlayer robot = (VirtualPlayer) p;
					robot.StrategyOffer();
				}
			} 
 	}
 	
 	/**
 	 * Each round after all the players have maken their offers, {@code showFacedUpOffers()} shows all the face-up offers of each players
 	 */
 	public void showFacedUpOffers() {
 		for (int np = 0; np < gameTable.playerlist.size(); np++) {
			for (int nc = 1; nc < 3; nc++) {
				if (gameTable.playerlist.get(np).getOffer().get(nc - 1).showface()) {
					System.out.print("player " + np + "(" + gameTable.playerlist.get(np).toString() + ")"
							+ " has a faceup card : ");
					System.out.println(gameTable.playerlist.get(np).getOffer().get(nc - 1).toString()
							);
				}
			}
		}
 	}
 	
 	
 	
 	
 	/**
 	 * {@code takeOffers(Player playerdecided)} allows the player to take a card from other peoples' offers.
 	 * @param playerdecided is the player who is going to take the offer.
 	 * if {@code playerdecided} is a virtual player, call the function {@code StrategyTake}.
 	 */
 	public void takeOffers(Player playerdecided) {
 		while (gameTable.fullofferPlayers().size() != 0) {

			if (playerdecided == null || playerdecided.hastakencard) {
				playerdecided = gameTable.decideOrder();
			}

			if (playerdecided instanceof HumanPlayer) {
				HumanPlayer human = (HumanPlayer) playerdecided;
				System.out.println();
				System.out.println(human + ":");
				LinkedList<Player> players = gameTable.fullofferPlayers();
				if ((players.size()>1)&&players.contains(playerdecided)) {
					players.remove(playerdecided);
				}
				
				System.out.println("You present Jest is as below:");
				playerdecided.checkJest();
				System.out.println("You can choose an offer from ");
				for (Player player : players) {
					System.out.println(player);
				}
				
				
				System.out.println(
						"Please take card : you can choose by the code of player and the card(0 for facedown, 1 for faceup):");
				int tnp = scan.nextInt();
				if (tnp < gameTable.playerlist.size()&&(gameTable.playerlist.get(tnp)!=human||(gameTable.fullofferPlayers().size()==1))) {   //make the situation where one can take his own card clear

					System.out.println(gameTable.playerlist.get(tnp).toString());
					human.takeOffer(gameTable.playerlist.get(tnp), scan.nextInt());
				}

				if (human.hastakencard) {
					playerdecided = gameTable.playerlist.get(tnp);
				}

			}

			else if (playerdecided instanceof VirtualPlayer) {
				VirtualPlayer robot = (VirtualPlayer) playerdecided;
				LinkedList<Player> offeredPlayers = gameTable.fullofferPlayers();
				if(offeredPlayers.size()>1) {                           ///make the situation where one can take his own card clear
					offeredPlayers.remove(robot);
				}
				playerdecided = robot.StrategyTake(offeredPlayers);
			}

		}
		// take offer one by one
		playerdecided = null;
 	}
 	
 	/**
 	 * The main function.
 	 * Inside the main function, we first get the unique instance of the game table.
 	 * Inside the function overwrote run(), the user can decide the number of human players.
 	 * Then corresponding numbers of the virtual players will be created.
 	 * After the player list, human player list and the virtual player list are created and elements are added, it will show all the players.
 	 * Then the game table will be set up and the game starts.
 	 * If there are still cards in the draw deck, cards are dealt and each player makes offer and takes offer.
 	 * If there are no card in the draw deck, each player takes the remained offer in front of them.
 	 * Then the winner will be decided and the game is over.
 	 */
 	public static void main(String[] args) {
		GameTable gameTable=getinstance();
		gameTable.consoleThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0; // number of human players
		int m = 0; // the number of a virtual player
		System.out.print("How many players are you?");

		while (scan.hasNextLine() && (i < 1 || i > 4)) {
			if (scan.hasNextInt()) {

				i = scan.nextInt();
			
				if (i < 5 & i > 0) {
					System.out.println("You have decided " + i + " players");
				} else {
					System.out.println("You must decide the number of players in the range of 1 to 4! Try again!");
				}
			}
		}

		for(int n=1;n<=i;n++) {

			System.out.println("Please enter the name of human player" + n);

			String name = scan.next();
			gameTable.createhumanplayer(name);
		}

		gameTable.createVirtualPlayers(i,m);
		gameTable.showPlayers();
		gameTable.setup();
		
		while (gameTable.hascard() == true) {
			System.out.println("----------------------------");

			gameTable.distribute();
			gameTable.dealcards();

			gameTable.makeOffer(i);

			gameTable.showFacedUpOffers();
			gameTable.takeOffers(gameTable.playerdecided);

			if (gameTable.hascard()) {
				gameTable.recycle();
			}
			else {
				
			}
		}

		for (Player p : gameTable.playerlist) {

			p.addToJest(p.getOffer().poll());
		}

		ScoreCalculator scoreCalculator = new ScoreCalculator();

		gameTable.accept(scoreCalculator);

		gameTable.assignTrophy();

		gameTable.accept(scoreCalculator);

		for (Player p : gameTable.playerlist) {
			System.out.print(p.toString());
			System.out.println(" has a set of jest as below: ");
			for (Card c : p.getJest()) {
				System.out.println(c.toString());
			}

			System.out.println(p.getscore());
		}

		System.out.println("winner is:");

		System.out.println(gameTable.decidewinner(gameTable.playerlist).toString());
       
		
		
		
	}
 	
 	
	public Thread getThread() {
		return this.consoleThread;
	}
	
 	
 	
 	
}