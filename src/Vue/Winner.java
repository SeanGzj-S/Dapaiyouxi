package Vue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
/**
 * The class {@code Winner} is used to show the winner.
 */
public class Winner {
	/**
	 * The window showing the winner.
	 */
	private JFrame frame;

	/**
	 * Constructor of the class {@code Winner}.
	 * @param string is the name of the winner.
	 */
	public Winner(String string) {
		initialize(string);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String string) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel(string);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Bodoni MT Black", Font.PLAIN, 30));
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}

	
	public JFrame getFrame() {
		return this.frame;
	}
}
