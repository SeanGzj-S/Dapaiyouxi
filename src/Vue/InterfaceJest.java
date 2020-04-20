package Vue;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Jest.Card;
import Jest.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The class {@code InterfaceJest} represents for the Jests of all the players.
 * It is a part of the design pattern Observer.
 * It should be update according to the changes happened in the observable class,
 * so it implements the class {@code Observer}.
 */
public class InterfaceJest implements Observer {
	/**
	 * {@code frame} is an instance of the {@code JFrame} which represents for the Jest window.
	 */
	private JFrame frame;
	/**
	 * A linked list of the cards in the Jests.
	 * Each card is an instance of {@code JLabel}.
	 */
    private LinkedList<JLabel> labels;
	/**
	 * Constructor of the class {@code InterfaceJest}.
	 * @param player is the player whose Jest will be showed.
	 */
	public InterfaceJest(Player player) {
		initialize(player);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param player is the player whose Jest will be showed.
	 * It will show the name of the player and all the graphs of his cards in his Jest.
	 */
	private void initialize(Player player) {
		frame = new JFrame(player.toString()+"'s Jest");
		labels=new LinkedList<JLabel>();
		frame.setBounds(100, 100, 1039, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(14, 13, 230, 330);
		frame.getContentPane().add(lblNewLabel);
		labels.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(264, 13, 230, 330);
		frame.getContentPane().add(label);
		labels.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(514, 13, 230, 330);
		frame.getContentPane().add(label_1);
		labels.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(14, 374, 230, 330);
		frame.getContentPane().add(label_2);
		labels.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(264, 374, 230, 330);
		frame.getContentPane().add(label_3);
		labels.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(514, 374, 230, 330);
		frame.getContentPane().add(label_4);
		labels.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(777, 13, 230, 330);
		frame.getContentPane().add(label_5);
		labels.add(label_5);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnClose.setBounds(777, 456, 200, 50);
		frame.getContentPane().add(btnClose);
	}

	/**
	 * Over write the function {@code update(Object o)}.
	 * @param o, if o is a type of {@code Player}, show all the cards in the Jest of the player. 
	 */
	public void update(Object o) {
		if (o instanceof Player) {
			Player player = (Player) o;
			int i =0;
			for(Card c : player.getJest()) {
				String sourcename1 = "/Vue/"+c.toString()+".PNG";

				ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
				image.setImage(image.getImage().getScaledInstance(230, 330,Image.SCALE_DEFAULT ));
		        labels.get(i).setIcon(image);
				i++;
			}
		}
	}
	/**
	 * To get attribut {@code frame}.
	 * @return type of the {@code JFrame}, the window of the Jest window.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

}
