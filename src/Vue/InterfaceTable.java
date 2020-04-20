package Vue;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Jest.Card;
import Jest.GameTable;
import Jest.Player;
import Jest.ScoreCalculator;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The class {@code InterfaceTable} represents for the game table.
 * It is a part of the design pattern Observer.
 * It should be update according to the changes happened in the observable class,
 * so it implements the class {@code Observer}.
 */
public class InterfaceTable implements Observer {
	/**
	 * {@code frame} is an instance of the {@code JFrame} which represents for the table window.
	 */
	private JFrame frame;
	/**
	 * A linked list of {@code JButton}s to store the eights cards on the game table.
	 * The eight cards are : each players' two offers.
	 * Each card is a {@code JLabel}.
	 */
	private LinkedList<JLabel> labels;
	/**
	 * Press the button to deal cards.
	 */
	private JButton dealCards;
	/**
	 * Press the button to let the next player to take offer.
	 */
	private JButton nextTake;
	/**
	 * Press the button to finish the game and to check the result.
	 */
	private JButton finalButton;
	/**
	 * A linked list of {@code JLabels}s which stores all the scores of the players.
	 */
	private LinkedList<JLabel> scoreLabels;
	/**
	 * A linked list of {@code JLabels}s which stores all the players.
	 */
    private LinkedList<JLabel> playerLabels;
    /**
     * A label shows that the offer has been taken.
     */
    private JLabel Taker;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceTable window = new InterfaceTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public InterfaceTable() {

		initialize();
		finalButton.setEnabled(false);
		
		

	}

	/**
	 * Over write the function {@code update(Object o)}.
	 * The game table shows all the players with the two offers they have made besides them.
	 * If they have made their offer in their own window,
	 * The offers on the game table will be changes(one of them will be face-up).
	 * If their offer is taken, the offer will be replaced by a label "taken".
	 */
	public void update(Object o) {
		if (o instanceof GameTable) {
			GameTable gameTable = (GameTable) o;
			int index =0;
			int scoreindex=0;		
			for(Player player : gameTable.playerlist) {
				if (player.offerIsComplete()) {
					for(Card card : player.getOffer()) {
						if (card.showface()) {
							String sourcename1 = "/Vue/"+card.toString()+".PNG";

							ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
							image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
							labels.get(index).setIcon(image);
							index++; 
						}
						else {
							String sourcename1 = "/Vue/CardBack.PNG";
							ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
							image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
							labels.get(index).setIcon(image);
							index++; 
						}
				}	
				}
				else if (player.getOffer().size()==1) {
					if (player.getOffer().get(0).showface()) {
						String sourcename1 = "/Vue/"+player.getOffer().get(0).toString()+".PNG";
						ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
						image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
						labels.get(index).setIcon(image);
						index++; 
					}
					else {
						String sourcename1 = "/Vue/CardBack.PNG";
						ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
						image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
						labels.get(index).setIcon(image);
						index++; 
					}
					
					ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource("/Vue/TAKEN.PNG"));
					image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
					labels.get(index).setIcon(image);
					index++; 	
				}
				else {
					ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource("/Vue/TAKEN.PNG"));
					image.setImage(image.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));
					labels.get(index).setIcon(image);
					labels.get(index++).setIcon(image);					
				}
				ScoreCalculator scoreCalculator = new ScoreCalculator();
				gameTable.accept(scoreCalculator);
				scoreindex++;
				
				scoreLabels.get(scoreindex-1).setText("p"+scoreindex+" "+player.toString()+" scores: "+player.getscore());
			}
			if (gameTable.playerlist.size()!=4) {
				playerLabels.get(3).setText(" ");
			}
			int j;
			for (int i = 0; i < gameTable.playerlist.size(); i++) {
				j=i+1;
				playerLabels.get(i).setText("p"+j+" "+gameTable.playerlist.get(i).toString());
			}
			
			
			
		}
	}
	/**
	 * Initialize the contents of the frame.
	 * The game table shows all the players with the two offers they have made besides them.
	 */
	private void initialize() {
		frame = new JFrame();
		labels = new LinkedList<JLabel>();
		scoreLabels=new LinkedList<JLabel>();
		playerLabels=new LinkedList<JLabel>();
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	/*	
		JLabel label = new JLabel("");
		ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource("/Vue/Background.png"));
		image.setImage(image.getImage().getScaledInstance(932, 553,Image.SCALE_DEFAULT ));
        label.setIcon(image);
		label.setBounds(0, 0, 932, 553);
		frame.getContentPane().add(label);*/
		
		ImageIcon image2 =new ImageIcon(InterfaceOffer.class.getResource("/Vue/CardBack.png"));
		image2.setImage(image2.getImage().getScaledInstance(136, 173,Image.SCALE_DEFAULT ));

		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(24, 13, 136, 173);
		frame.getContentPane().add(label_1);
		labels.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(166, 13, 136, 173);
		frame.getContentPane().add(label_2);
		labels.add(label_2);
		
		JLabel lblPlayer = new JLabel("player 1");
		lblPlayer.setBounds(86, 210, 143, 18);
		frame.getContentPane().add(lblPlayer);
		playerLabels.add(lblPlayer);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(365, 13, 136, 173);
		frame.getContentPane().add(label_3);
		labels.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(507, 13, 136, 173);
		frame.getContentPane().add(label_4);
		labels.add(label_4);

		
		JLabel lblPlayer_1 = new JLabel("player 2");
		lblPlayer_1.setBounds(427, 210, 150, 18);
		frame.getContentPane().add(lblPlayer_1);
		playerLabels.add(lblPlayer_1);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(24, 280, 136, 173);
		frame.getContentPane().add(label_5);
		labels.add(label_5);

		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(166, 280, 136, 173);
		frame.getContentPane().add(label_6);
		labels.add(label_6);

		
		JLabel lblPlayer_2 = new JLabel("player 3");
		lblPlayer_2.setBounds(86, 477, 143, 18);
		frame.getContentPane().add(lblPlayer_2);
		playerLabels.add(lblPlayer_2);
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(365, 280, 136, 173);
		frame.getContentPane().add(label_7);
		labels.add(label_7);

		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(507, 280, 136, 173);
		frame.getContentPane().add(label_8);
		labels.add(label_8);

		
		JLabel lblPlayer_3 = new JLabel("player 4");
		lblPlayer_3.setBounds(427, 477, 150, 18);
		frame.getContentPane().add(lblPlayer_3);
		playerLabels.add(lblPlayer_3);
		
		dealCards = new JButton("DealCards");
		dealCards.setBounds(709, 148, 113, 27);
		frame.getContentPane().add(dealCards);
		
		nextTake = new JButton("NextTake");
		nextTake.setBounds(709, 201, 113, 27);
		frame.getContentPane().add(nextTake);
		
		finalButton = new JButton("Final");
		finalButton.setBounds(709, 255, 113, 27);
		frame.getContentPane().add(finalButton);
		
		JLabel score1 = new JLabel("New label");
		score1.setBounds(718, 328, 200, 18);
		frame.getContentPane().add(score1);
		scoreLabels.add(score1);
		
		JLabel score2 = new JLabel("New label");
		score2.setBounds(718, 359, 200, 18);
		frame.getContentPane().add(score2);
		
		JLabel score3 = new JLabel("New label");
		score3.setBounds(718, 390, 200, 18);
		frame.getContentPane().add(score3);
		
		JLabel score4 = new JLabel("");
		score4.setBounds(718, 421, 150, 18);
		frame.getContentPane().add(score4);
	
		

		scoreLabels.add(score2);
		scoreLabels.add(score3);
		scoreLabels.add(score4);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(709, 51, 143, 18);
		frame.getContentPane().add(lblNewLabel);
		Taker= lblNewLabel;

		
		for(JLabel jLabel : labels) {
			jLabel.setIcon(image2);
		}
		
	}
	
	public LinkedList<JLabel> getcardsontable(){
		return labels;
	}
	
	public JLabel gettaker() {
		return Taker;
	}
	
	public JButton getdealcardButton() {
		return dealCards;
	}
	
	public JButton getnexttakeButton() {
		return nextTake;
	}
	
	public JButton getfinalButton() {
		return finalButton;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public LinkedList<JLabel> getplayerLabels(){
		return playerLabels;
	}
}
