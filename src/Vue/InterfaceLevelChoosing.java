package Vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class InterfaceLevelChoosing {
	/**
	 * {@code frame} is an instance of the {@code JFrame} enables the user to choose the level of the virtual players.
	 */
	private JFrame frame;
	/**
	 * Three buttons in the window for choose, 
	 * represents three different level of the virtual player.
	 * Each button is a {@code JButton}.
	 */
	private JButton Easy,Normal,Tough;
	
	
	/**
	 * Launch the application.
	 * In the main function, the user can choose the level of
	 * the virtual player(s) by pressing the corresponding button.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceLevelChoosing window = new InterfaceLevelChoosing(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param i is the number of the virtual players.
	 */
	public InterfaceLevelChoosing(int i) {
		initialize(i);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param i is the number of the virtual players.
	 * The user can choose the difficulty level of the virtual players.
	 */
	private void initialize(int i) {
		frame = new JFrame("choosing the level of virtual player "+ i);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChooseTheLevel = new JLabel("Choose the level of your competitor");
		lblChooseTheLevel.setBounds(43, 29, 348, 50);
		frame.getContentPane().add(lblChooseTheLevel);
		
		Easy = new JButton("Easy");
		Easy.setBounds(151, 92, 113, 27);
		frame.getContentPane().add(Easy);
		
		Normal = new JButton("Normal");
		Normal.setBounds(151, 141, 113, 27);
		frame.getContentPane().add(Normal);
		
		Tough = new JButton("Tough");
		Tough.setBounds(151, 192, 113, 27);
		frame.getContentPane().add(Tough);
	}
	
	/**
	 * To get the attribut {@code Easy}.
	 * @return type of {@code JButton}, the button representing for the easy level.
	 */
	public JButton geteasyButton() {
		return Easy;
	}
	/**
	 * To get the attribut {@code Normal}.
	 * @return type of {@code JButton}, the button representing for the normal level.
	 */
	public JButton getnormalButton() {
		return Normal;
	}
	/**
	 * To get the attribut {@code Tough}.
	 * @return type of {@code JButton}, the button representing for the tough level.
	 */
	public JButton gettoughButton() {
		return Tough;
	}
	/**
	 * To get the attribut {@code frame}.
	 * @return type of {@code JFrame}, the level choosing window.
	 */
	public JFrame getFrame() {
		return frame;
	}
	
}
