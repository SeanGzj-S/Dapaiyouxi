package Vue;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InterfaceName {
	/**
	 * {@code frame} is an instance of the {@code JFrame} which enables the users to enter their names.
	 */
	private JFrame frame;
	/**
	 * A text field for the user to enter in his/her name.
	 */
	private JTextField textField;
	/**
	 * A button to press after the name is entered.
	 */
	private JButton okButton;

	/**
	 * Launch the application.
	 * Each human player enter their names.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceName window = new InterfaceName(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param n is the number of the human players.
	 */
	public InterfaceName(int n) {
		initialize( n);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param n is the number of the human players.
	 * For each player, there will be a window to enter his/her name.
	 */
	private void initialize(int n) {
		frame = new JFrame("human player " + n);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterYourName = new JLabel("Enter your name:");
		lblEnterYourName.setBounds(57, 85, 131, 18);
		frame.getContentPane().add(lblEnterYourName);
		
		textField = new JTextField();
		textField.setBounds(206, 85, 156, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		okButton = new JButton("ok");
		okButton.setBounds(125, 150, 150, 20);
		frame.getContentPane().add(okButton);
	
	}
	
	/**
	 * To get the attribut {@code frame}.
	 * @return frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * To get the attribut {@code textField}.
	 * @return textField.
	 */
	public JTextField geTextField() {
		return textField;
	}
	/**
	 * To get the attribut {@code okButton}.
	 * @return okButton.
	 */
	public JButton getokButton() {
		return okButton;
	}


}


