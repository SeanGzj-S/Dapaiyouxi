package Vue;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
/**
 * The interface {@code InterfaceInitiale} represents for the initial interface of the game.
 * It contains a window and a button to start.
 */
public class InterfaceInitiale {
	/**
	 * {@code frame} is an instance of the {@code JFrame} which represents for the initial window.
	 */
	private JFrame frame;
	/**
	 * {@code initialeButton} is an instance of the {@code JButton} which represents for the button to start.
	 */
    private JButton initialeButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceInitiale window = new InterfaceInitiale();
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
	public InterfaceInitiale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * It will show a picture of the game and the button "Ready and go".
	 * If you press the button, the game will start.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(104, 0, 227, 168);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(InterfaceInitiale.class.getResource("/Vue/DisplayJest.PNG")));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnReadyAndGo = new JButton("Ready and go\uFF01");
		btnReadyAndGo.setBounds(135, 196, 171, 27);
		frame.getContentPane().add(btnReadyAndGo);
	    initialeButton = btnReadyAndGo;
	}
	/**
	 * To get the attribut {@code initialeButton}
	 * @return type of {@code JButton}, the initialeButton.
	 */
	public JButton getinitialeButton() {
		return initialeButton;
	}
	/**
	 * To get the attribut {@code frame}
	 * @return type of {@code frame}, the frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
	}
