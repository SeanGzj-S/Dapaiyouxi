package Controler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;

import Jest.Easy;
import Jest.GameTable;
import Jest.HumanPlayer;
import Jest.Normal;
import Jest.Player;
import Jest.ScoreCalculator;
import Jest.Tough;
import Jest.VirtualPlayer;
import Vue.*;
/**
 * The class {@code Controler} collects users' operations and translates them onto the model.
 * Because of concurrent programming, it should contain a thread
 * which enables the player to play the game from graphic interface.
 * Therefore, it implements the class {@code Runnable}.
 */
public class Controler implements Runnable {

	/**
	 * The controller contains an attribut {@code GameTable}.
	 */
	private GameTable gameTable;
	/**
	 * Because of concurrent programming, it contains a thread
	 * which enables the player to play the game from graphic interface.
	 */
	private Thread vueThread;
	/**
	 * A {@code JButton} to start the game.
	 */
	private JButton btninitiale;
	/**
	 * A {@code JButton} to show offers.
	 */
	private JButton showofferButton;
	/**
	 * A {@code JButton} allows the first human player to face down the 
	 * left one of the two cards dealt to the player.
	 */
	private JButton facedown1;
	/**
	 * A {@code JButton} allows the first human player to face down the 
	 * right one of the two cards dealt to the player.
	 */
	private JButton facedown2;
	/**
	 * A {@code JButton} allows the second human player to face down the 
	 * left one of the two cards dealt to the player.
	 */
	private JButton P2facedown1;
	/**
	 * A {@code JButton} allows the second human player to face down the 
	 * left one of the two cards dealt to the player.
	 */
	private JButton P2facedown2;
	/**
	 * A {@code JButton} to decide one human player.
	 */
	private JButton oneplayerButton;
	/**
	 * A {@code JButton} to decide two human players.
	 */
	private JButton twoplayerButton;
	/**
	 * A {@code JButton} to decide three human players.
	 */
	private JButton threeplayerButton;
	/**
	 * A {@code JButton} to decide four human players.
	 */
	private JButton fourplayerButton;
	/**
	 * A {@code JButton} to deal cards.
	 */
	private JButton dealcardButton;
	/**
	 * A {@code JButton} to let the next person take.
	 */
	private JButton nexttakeButton;
	/**
	 * A {@code JButton} to finish the game.
	 */
	private JButton finalButton;
	/**
	 * A linked list of {@code JButton}s to store the eights cards on the game table.
	 * The eight cards are : each players' two offers.
	 * Each card is a {@code JLabel}.
	 */
	private LinkedList<JLabel> cardsonthetable;
	/**
	 * A linked list of the windows which let human player to enter their names.
	 * Each window is an instance of the class {@code InterfaceName}.
	 */
	private LinkedList<InterfaceName> humannames;
	/**
	 * A linked list of the windows which let human player to choose the level of the virtual player.
	 * Each window is an instance of the class {@code robotsChoosings}.
	 */
	private LinkedList<InterfaceLevelChoosing> robotsChoosings;
	/**
	 * {@code vueTexte} represents for the thread of the command line when playing with graphic interface.
	 */
	private VueTexte vueTexte;
	
	/**
	 * Constructor of the class {@code Controler}.
	 * Get the unique instance of the class {@code GameTable}.
	 * Create a new linked list of the attributs {@code hummannames}, {@code robotsChoosings}, {@code vueTexte}, {@code vueThreand}.
	 */
	public Controler() {
		gameTable = GameTable.getinstance();
		humannames=new LinkedList<InterfaceName>();
		robotsChoosings=new LinkedList<InterfaceLevelChoosing>();
		vueThread= new Thread(this);
		vueTexte = new VueTexte(gameTable);
	}
	/**
	 * The main function.
	 * Here we create a new instance of the class {@code Controler}.
	 * And then start the thread of the graphic interface.
	 */
	public static void main(String[] args) {
		Controler controler =new Controler();
		controler.vueThread.start();
		
	}
	/**
	 * We over write the function {@code run()} of the thread {@code vueThread}.
	 * We create all the interfaces needed to be showed.
	 * We add action listeners to the buttons.
	 */
	public void run() {
		InterfaceInitiale interfaceInitiale = new InterfaceInitiale();
		InterfacePlayerNumber interfacePlayerNumber = new InterfacePlayerNumber();
		InterfaceTable main = new InterfaceTable();
		LinkedList<InterfaceJest> Jestlist = new LinkedList<InterfaceJest>();								
		humannames.add(new InterfaceName(1));
		humannames.add(new InterfaceName(2));
		humannames.add(new InterfaceName(3));
		humannames.add(new InterfaceName(4));
		robotsChoosings.add(new InterfaceLevelChoosing(1));
		robotsChoosings.add(new InterfaceLevelChoosing(2));
		
		
		robotsChoosings.get(0).getFrame().setVisible(false);
		robotsChoosings.get(1).getFrame().setVisible(false);
		for (InterfaceName interfaceName : humannames) {
			interfaceName.getFrame().setVisible(false);
		}
		interfaceInitiale.getFrame().setVisible(true);
		interfacePlayerNumber.getFrame().setVisible(false);
		main.getFrame().setVisible(false);
		btninitiale = interfaceInitiale.getinitialeButton();
		oneplayerButton=interfacePlayerNumber.getoneplayerButton();
		twoplayerButton=interfacePlayerNumber.gettwoplayerButton();
		threeplayerButton=interfacePlayerNumber.getthreeplayerButton();
		fourplayerButton=interfacePlayerNumber.getfourplayerButton();
		showofferButton=gameTable.InterfaceOffer.getshowofferButton();
		facedown1=gameTable.InterfaceOffer.getfacedown1();
		facedown2=gameTable.InterfaceOffer.getfacedown2();
		P2facedown1=gameTable.interfaceOffer2.getfacedown1();
		P2facedown2=gameTable.interfaceOffer2.getfacedown2();
		dealcardButton=main.getdealcardButton();
		nexttakeButton=main.getnexttakeButton();
		finalButton=main.getfinalButton();
		cardsonthetable=main.getcardsontable();
		
		btninitiale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interfaceInitiale.getFrame().setVisible(false);
				interfacePlayerNumber.getFrame().setVisible(true);

			}
		});
		
		oneplayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				interfacePlayerNumber.getFrame().setVisible(false);

				robotsChoosings.get(0).getFrame().setVisible(true);
				robotsChoosings.get(1).getFrame().setVisible(true);
				
				for (int i = 0; i < 1; i++) {
					humannames.get(i).getFrame().setVisible(true);
				}
			}
		});	
		
		
		twoplayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				interfacePlayerNumber.getFrame().setVisible(false);
				
				robotsChoosings.get(0).getFrame().setVisible(true);
				for (int i = 0; i < 2; i++) {
					humannames.get(i).getFrame().setVisible(true);
				}
			}
		});
		
		threeplayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				interfacePlayerNumber.getFrame().setVisible(false);
				for (int i = 0; i < 3; i++) {
					humannames.get(i).getFrame().setVisible(true);
				}
			}
		});
		
		
		fourplayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				interfacePlayerNumber.getFrame().setVisible(false);

				for (int i = 0; i < 4; i++) {
					humannames.get(i).getFrame().setVisible(true);
				}
			}
		});
		
		for(InterfaceName interfaceName :humannames) {
			interfaceName.getokButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				gameTable.createhumanplayer(interfaceName.geTextField().getText());				 
				 interfaceName.getFrame().setVisible(false);
				 main.getFrame().setVisible(true);
				 gameTable.InterfaceOffer.getFrame().setTitle(gameTable.humanPlayers.get(0).toString());
				 gameTable.InterfaceOffer.getFrame().setVisible(true);
				 if (gameTable.humanPlayers.size()==2) {
					 gameTable.interfaceOffer2.getFrame().setTitle(gameTable.humanPlayers.get(1).toString());
					 gameTable.interfaceOffer2.getFrame().setVisible(true);
				}
				}
			});	
		}
		
		for (InterfaceLevelChoosing interfaceLevelChoosing : robotsChoosings) {
			interfaceLevelChoosing.geteasyButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					gameTable.createvirtualplayer(gameTable.playerlist.size(), new Easy());
					interfaceLevelChoosing.getFrame().setVisible(false);
				}
			});
			
			interfaceLevelChoosing.getnormalButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					gameTable.createvirtualplayer(gameTable.playerlist.size(), new Normal());
					interfaceLevelChoosing.getFrame().setVisible(false);
				}
			});
			
			interfaceLevelChoosing.gettoughButton().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					gameTable.createvirtualplayer(gameTable.playerlist.size(), new Tough());
					interfaceLevelChoosing.getFrame().setVisible(false);
				}
			});
			
			
		}
		
		facedown1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameTable.humanPlayers.get(0).getOffer().get(1).faceup();
				gameTable.humanPlayers.get(0).hastakencard=false;
				gameTable.InterfaceOffer.update(gameTable.humanPlayers.get(0));
				main.update(gameTable);
				gameTable.InterfaceOffer.getFrame().setVisible(false);
				nexttakeButton.setEnabled(true);
			}
		});
		
		facedown2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameTable.humanPlayers.get(0).getOffer().get(0).faceup();
				gameTable.humanPlayers.get(0).hastakencard=false;
				gameTable.InterfaceOffer.update(gameTable.humanPlayers.get(0));
				main.update(gameTable);
				gameTable.InterfaceOffer.getFrame().setVisible(false);
				nexttakeButton.setEnabled(true);
			}
		});
		
       P2facedown1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameTable.humanPlayers.get(1).getOffer().get(1).faceup();
				gameTable.humanPlayers.get(1).hastakencard=false;
				gameTable.interfaceOffer2.update(gameTable.humanPlayers.get(1));
				main.update(gameTable);
				gameTable.interfaceOffer2.getFrame().setVisible(false);
				nexttakeButton.setEnabled(true);
			}
		});
		
		P2facedown2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameTable.humanPlayers.get(1).getOffer().get(0).faceup();
				gameTable.humanPlayers.get(1).hastakencard=false;
				gameTable.interfaceOffer2.update(gameTable.humanPlayers.get(1));
				main.update(gameTable);
				gameTable.interfaceOffer2.getFrame().setVisible(false);
				nexttakeButton.setEnabled(true);
			}
		});
		
        this.dealcardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (Jestlist.size()==0) {
					for(Player player : gameTable.playerlist) {
					Jestlist.add(new InterfaceJest(player));	
					}
				}
				
				if (gameTable.cardnumber!=0) {
					if (gameTable.playerlist.get(0).getOffer().size()!=0) {
						gameTable.recycle();
					}
					gameTable.setup();
					gameTable.distribute();
					gameTable.dealcards();
					if(gameTable.humanPlayers.size()==1) {
						gameTable.InterfaceOffer.update(gameTable.humanPlayers.get(0));
						gameTable.InterfaceOffer.getFrame().setVisible(true);
					}
					if(gameTable.humanPlayers.size()==2) {
						gameTable.InterfaceOffer.update(gameTable.humanPlayers.get(0));
						gameTable.InterfaceOffer.getFrame().setVisible(true);
						gameTable.interfaceOffer2.update(gameTable.humanPlayers.get(1));
						gameTable.interfaceOffer2.getFrame().setVisible(true);
					}	
					for(VirtualPlayer virtualPlayer : gameTable.virtualPlayers) {
						virtualPlayer.StrategyOffer();
					}
					dealcardButton.setEnabled(false);
				}
				else {

					finalButton.setEnabled(true);
				}
			}
		});
		
		
		nexttakeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (gameTable.fullofferPlayers().size()!=0) {
					if (gameTable.playerdecided == null || gameTable.playerdecided.hastakencard()) {
						gameTable.playerdecided = gameTable.decideOrder();
					}

					main.gettaker().setText(gameTable.playerdecided.toString());
					if (gameTable.playerdecided instanceof HumanPlayer) {
						
						vueTexte.begin();
						HumanPlayer human = (HumanPlayer) gameTable.playerdecided;
						LinkedList<Player> players = gameTable.fullofferPlayers();
						if ((players.size()>1)&&players.contains(gameTable.playerdecided)) {
							players.remove(gameTable.playerdecided);
						}
						
						nexttakeButton.setEnabled(false);
					}

					else if (gameTable.playerdecided instanceof VirtualPlayer) {
						VirtualPlayer robot = (VirtualPlayer) gameTable.playerdecided;
						LinkedList<Player> offeredPlayers = gameTable.fullofferPlayers();
						if(offeredPlayers.size()>1) {                           ///make the situation where one can take his own card clear
							offeredPlayers.remove(robot);
						}
						gameTable.playerdecided = robot.StrategyTake(offeredPlayers);
					}
					
					main.update(gameTable);

				}
				else if (gameTable.fullofferPlayers().size()==0&&gameTable.hascard()) {
					
					dealcardButton.setEnabled(true);
					nexttakeButton.setEnabled(false);
				}
				else {
					nexttakeButton.setEnabled(false);
					finalButton.setEnabled(true);
				}
				}
				

		});
		
		finalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				for (Player p : gameTable.playerlist) {
					p.addToJest(p.getOffer().pollFirst());
				}
				ScoreCalculator scoreCalculator = new ScoreCalculator();

				gameTable.accept(scoreCalculator);

				gameTable.assignTrophy();
				

				gameTable.accept(scoreCalculator);
				
				
				main.update(gameTable);
				
				for (int i = 0; i < Jestlist.size(); i++) {
					Jestlist.get(i).update(gameTable.playerlist.get(i));
					Jestlist.get(i).getFrame().setVisible(true);
				}
				
				Winner winner = new Winner("Winner is "+gameTable.decidewinner(gameTable.playerlist).toString()+" !");
				winner.getFrame().setVisible(true);
				System.out.println(gameTable.decidewinner(gameTable.playerlist).toString());
			}
		});
		

		cardsonthetable.get(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				if (vueTexte.getstate()) {
					gameTable.playerdecided.addToJest(gameTable.playerlist.get(0).getOffer().get(0));
					gameTable.playerdecided.hastakencard=true;
					gameTable.playerlist.get(0).getOffer().remove(gameTable.playerlist.get(0).getOffer().get(0));
					gameTable.playerdecided=gameTable.playerlist.get(0);
					if (gameTable.fullofferPlayers().size()==0) {
						dealcardButton.setEnabled(true);
						nexttakeButton.setEnabled(false);
					}
					nexttakeButton.setEnabled(true);
					vueTexte.stop();
				}
				
				if (vueTexte.hasdone) {
					nexttakeButton.setEnabled(true);
				}

				main.update(gameTable);
			}
		});
		
		cardsonthetable.get(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			if (vueTexte.getstate()) {
				gameTable.playerdecided.addToJest(gameTable.playerlist.get(0).getOffer().get(1));
				gameTable.playerdecided.hastakencard=true;
				gameTable.playerlist.get(0).getOffer().remove(gameTable.playerlist.get(0).getOffer().get(1));
				gameTable.playerdecided=gameTable.playerlist.get(0);
				if (gameTable.fullofferPlayers().size()==0) {
					dealcardButton.setEnabled(true);
					nexttakeButton.setEnabled(false);
				}
				nexttakeButton.setEnabled(true);
				vueTexte.stop();
			}
			
			if (vueTexte.hasdone) {
				nexttakeButton.setEnabled(true);
			}

			main.update(gameTable);
		}
		});
		
		cardsonthetable.get(2).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				if (vueTexte.getstate()) {
					gameTable.playerdecided.addToJest(gameTable.playerlist.get(1).getOffer().get(0));
					gameTable.playerdecided.hastakencard=true;
					gameTable.playerlist.get(1).getOffer().remove(gameTable.playerlist.get(1).getOffer().get(0));
					gameTable.playerdecided=gameTable.playerlist.get(1);
					if (gameTable.fullofferPlayers().size()==0) {
						dealcardButton.setEnabled(true);
						nexttakeButton.setEnabled(false);
					}
					nexttakeButton.setEnabled(true);
					vueTexte.stop();
				}
				
				if (vueTexte.hasdone) {
					nexttakeButton.setEnabled(true);
				}

				main.update(gameTable);
			}
		});
		
		cardsonthetable.get(3).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (vueTexte.getstate()) {
					gameTable.playerdecided.addToJest(gameTable.playerlist.get(1).getOffer().get(1));
					gameTable.playerdecided.hastakencard=true;
					gameTable.playerlist.get(1).getOffer().remove(gameTable.playerlist.get(1).getOffer().get(1));
					gameTable.playerdecided=gameTable.playerlist.get(1);
					if (gameTable.fullofferPlayers().size()==0) {
						dealcardButton.setEnabled(true);
						nexttakeButton.setEnabled(false);
					}
					nexttakeButton.setEnabled(true);
					vueTexte.stop();
				}
				
				if (vueTexte.hasdone) {
					nexttakeButton.setEnabled(true);
				}

				main.update(gameTable);
			}
		});
		
		cardsonthetable.get(4).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				if (vueTexte.getstate()) {
					gameTable.playerdecided.addToJest(gameTable.playerlist.get(2).getOffer().get(0));
					gameTable.playerdecided.hastakencard=true;
					gameTable.playerlist.get(2).getOffer().remove(gameTable.playerlist.get(2).getOffer().get(0));
					gameTable.playerdecided=gameTable.playerlist.get(2);
					if (gameTable.fullofferPlayers().size()==0) {
						dealcardButton.setEnabled(true);
						nexttakeButton.setEnabled(false);
					}
					nexttakeButton.setEnabled(true);
					vueTexte.stop();
				}
				
				if (vueTexte.hasdone) {
					nexttakeButton.setEnabled(true);
				}

				main.update(gameTable);
			}
		});
		
		cardsonthetable.get(5).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (vueTexte.getstate()) {
					gameTable.playerdecided.addToJest(gameTable.playerlist.get(2).getOffer().get(1));
					gameTable.playerdecided.hastakencard=true;
					gameTable.playerlist.get(2).getOffer().remove(gameTable.playerlist.get(2).getOffer().get(1));
					gameTable.playerdecided=gameTable.playerlist.get(2);
					if (gameTable.fullofferPlayers().size()==0) {
						dealcardButton.setEnabled(true);
						nexttakeButton.setEnabled(false);
					}
					nexttakeButton.setEnabled(true);
					vueTexte.stop();
				}
				
				if (vueTexte.hasdone) {
					nexttakeButton.setEnabled(true);
				}

				main.update(gameTable);
			}
		});
		
		cardsonthetable.get(6).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				if (gameTable.playerlist.size()==4) {
					if (vueTexte.getstate()) {
						gameTable.playerdecided.addToJest(gameTable.playerlist.get(3).getOffer().get(0));
						gameTable.playerdecided.hastakencard=true;
						gameTable.playerlist.get(3).getOffer().remove(gameTable.playerlist.get(3).getOffer().get(0));
						gameTable.playerdecided=gameTable.playerlist.get(3);
						if (gameTable.fullofferPlayers().size()==0) {
							dealcardButton.setEnabled(true);
							nexttakeButton.setEnabled(false);
						}
						nexttakeButton.setEnabled(true);
						vueTexte.stop();
					}
					
					if (vueTexte.hasdone) {
						nexttakeButton.setEnabled(true);
					}

					main.update(gameTable);
				}
				else {
					cardsonthetable.get(6).setEnabled(false);
				}
			}
		});
		
		cardsonthetable.get(7).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				if (gameTable.playerlist.size()==4) {
					if (vueTexte.getstate()) {
						gameTable.playerdecided.addToJest(gameTable.playerlist.get(3).getOffer().get(1));
						gameTable.playerdecided.hastakencard=true;
						gameTable.playerlist.get(3).getOffer().remove(gameTable.playerlist.get(3).getOffer().get(1));
						gameTable.playerdecided=gameTable.playerlist.get(3);
						if (gameTable.fullofferPlayers().size()==0) {
							dealcardButton.setEnabled(true);
							nexttakeButton.setEnabled(false);
						}
						vueTexte.stop();
						nexttakeButton.setEnabled(true);
					}
					
					if (vueTexte.hasdone) {
						nexttakeButton.setEnabled(true);
					}

					main.update(gameTable);
				}
				else {
					cardsonthetable.get(7).setEnabled(false);
				}
			}
		});
		

		main.getplayerLabels().get(0).addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
			Jestlist.get(0).update(gameTable.playerlist.get(0));
			Jestlist.get(0).getFrame().setVisible(true);
			}
		});
		
		main.getplayerLabels().get(0).addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
			Jestlist.get(1).update(gameTable.playerlist.get(1));
			Jestlist.get(1).getFrame().setVisible(true);

			}
		});
		
		main.getplayerLabels().get(0).addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
			Jestlist.get(2).update(gameTable.playerlist.get(2));
			Jestlist.get(2).getFrame().setVisible(true);
			}
		});
		
		if (gameTable.playerlist.size()==4) {
			main.getplayerLabels().get(0).addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent arg0) {
				Jestlist.get(3).update(gameTable.playerlist.get(3));
				Jestlist.get(3).getFrame().setVisible(true);
				}
			});
		}
	}

	
    public void disableTake() {
		for (JLabel label : cardsonthetable) {
			label.setEnabled(false);
		}
	}
	
    public void enableTake() {
		for (JLabel label : cardsonthetable) {
			label.setEnabled(true);
		}
    }
	
}
