package Vue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Jest.HumanPlayer;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The class {@code InterfaceOffer} represents for the interface showed to each player.
 * from where they can see the two cards dealt to them in each round.
 */
public class InterfaceOffer implements Observer{
	/**
	 * {@code frame} is an instance of the {@code JFrame} which represents for the offer window.
	 */
	private JFrame frame;
	/**
	 * The description of the offer on the left.
	 */
	private String card1;
	/**
	 * The description of the offer on the right.
	 */
	private String card2;
	/**
	 * The graph of the offer on the left.
	 * The graph is a {@code JLable}.
	 */
	private JLabel lbl1;
	/**
	 * The graph of the offer on the right.
	 * The graph is a {@code JLable}.
	 */
	private JLabel lbl2;
	/**
	 * Press the button to show offers.
	 */
	private JButton showofferButton;
	/**
	 * Press the button to face down the offer on the left.
	 */
	private JButton facedown1;
	/**
	 * Press the button to face down the offer on the right.
	 */
	private JButton facedown2;

	/**
	 * Create the application.
	 */
	public InterfaceOffer() {
		this.card1="CardBack";
		this.card2="CardBack";
		initialize();
	}
	/**
	 * To update the two offers of each player.
	 * @param object, if it is a {@code HumanPlayer}, his both offers will be showed to himself.
	 */
	public void update(Object object) {
		
		if (object instanceof HumanPlayer) {
			HumanPlayer humanPlayer = (HumanPlayer) object;
			card1=humanPlayer.getOffer().get(0).toString();
			card2=humanPlayer.getOffer().get(1).toString();
			this.setImage();
		}
	}
	
	public void setImage() {
		String sourcename1 = "/Vue/"+this.card1+".PNG";
		String sourcename2 = "/Vue/"+this.card2+".PNG";

		ImageIcon image =new ImageIcon(InterfaceOffer.class.getResource(sourcename1));
		image.setImage(image.getImage().getScaledInstance(230, 330,Image.SCALE_DEFAULT ));
        lbl1.setIcon(image);
		
		
		ImageIcon image2 =new ImageIcon(InterfaceOffer.class.getResource(sourcename2));
		image2.setImage(image2.getImage().getScaledInstance(230, 330,Image.SCALE_DEFAULT ));
        lbl2.setIcon(image2);
		
	}

	
	/**
	 * Initialize the contents of the frame.
	 * In the window of each person, he can see the two cards dealt to him.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 885, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbl1 = new JLabel("");
		lbl1.setBounds(97, 64, 230, 330);
		frame.getContentPane().add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setBounds(555, 64, 230, 330);
		frame.getContentPane().add(lbl2);
	    this.setImage();

        
		
		showofferButton = new JButton("ShowOffer");
		showofferButton.setBounds(342, 13, 200, 50);
		frame.getContentPane().add(showofferButton);
		
		facedown1 = new JButton("Face Down 1");
		facedown1.setBounds(113, 440, 200, 50);
		frame.getContentPane().add(facedown1);
		
		facedown2 = new JButton("Face Down 2");
		facedown2.setBounds(572, 440, 200, 50);
		frame.getContentPane().add(facedown2);
		
		

	}
	public JFrame getFrame() {
		return this.frame;
	}
	public JButton getshowofferButton() {
		return showofferButton;
	}
	
	public JButton getfacedown1() {
		return facedown1;
	}
	
	public JButton getfacedown2() {
		return facedown2;
	}
	
}
