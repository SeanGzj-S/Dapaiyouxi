package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * The class {@code InterfacePlayerNumber} represents for the window
 * which offers the choice of the human player numbers.
 * The user can choose from 1 to 4 human players by pressing the corresponding button.
 */
public class InterfacePlayerNumber {
	/**
	 * {@code frame} is an instance of the {@code JFrame}
	 * which represents for the player number decide window.
	 */
	private JFrame frame;
	/**
	 * The button presenting for 1 human player.
	 */
	private JButton oneplayerButton;
	/**
	 * The button presenting for 2 human players.
	 */
	private JButton twoplayerButton;
	/**
	 * The button presenting for 3 human players.
	 */
	private JButton threeplayerButton;
	/**
	 * The button presenting for 4 human players.
	 */
	private JButton fourplayerButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePlayerNumber window = new InterfacePlayerNumber();
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
	public InterfacePlayerNumber() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * There will be a question "How many players are you?"
	 * and the four buttons to press to choose.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 722, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("How many players are you?");
		lblNewLabel.setBounds(236, 65, 288, 50);
		frame.getContentPane().add(lblNewLabel);
		
		oneplayerButton = new JButton("1");
		oneplayerButton.setBounds(55, 210, 113, 27);
		frame.getContentPane().add(oneplayerButton);
		
		twoplayerButton = new JButton("2");
		twoplayerButton.setBounds(221, 210, 113, 27);
		frame.getContentPane().add(twoplayerButton);
		
		threeplayerButton = new JButton("3");
		threeplayerButton.setBounds(393, 210, 113, 27);
		frame.getContentPane().add(threeplayerButton);
		
		fourplayerButton = new JButton("4");
		fourplayerButton.setBounds(563, 210, 113, 27);
		frame.getContentPane().add(fourplayerButton);
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public JButton getoneplayerButton(){
		return oneplayerButton;
	}
	
	public JButton gettwoplayerButton(){
		return twoplayerButton;
	}
	
	public JButton getthreeplayerButton(){
		return threeplayerButton;
	}
	
	public JButton getfourplayerButton(){
		return fourplayerButton;
	}
}
