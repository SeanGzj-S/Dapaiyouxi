package Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Jest.GameTable;
import Jest.HumanPlayer;
import Jest.Player;

/**
 * The class {@code VueTexte} represents for the command line
 * when playing with the graphic interface.
 * It should be run at the same time with the thread of the graphic interface.
 * So it implements the interface {@code Runnable}.
 * It should be updated according to the changes happened in the observable class,
 * so it implements the class {@code Observer}.
 */
public class VueTexte implements Observer,Runnable {
	/**
	 * The game table.
	 */
	private GameTable gameTable;
	/**
	 * The active status of the thread.
	 */
	private boolean TakeActive;
	/**
	 * {@code hasdone} shows whether it has been done or not.
	 */
	public boolean hasdone;
	
	/**
	 * Constructor of the class {@code VueTexte}.
	 * @param gTable is the {@code GameTable} to be initialized.
	 * By default, the thread is not active and haven't been done yet.
	 */
	public VueTexte(GameTable gTable) {
		gameTable=gTable;
		TakeActive=false;
		hasdone=false;
	}
	/**
	 * To start the thread.
	 */
	public void begin() {
		TakeActive=true;
		hasdone=false;
		Thread thread =new Thread(this);
		thread.start();
	}
	
	/**
	 * To get the status of the thread.
	 * @return type of boolean showing whether the thread is active or not.
	 */
	public boolean getstate() {
		return TakeActive;
	}
	
	private int readInt() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String chaine = null;
		try {
			System.out.println(">>>>>>");
			chaine=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int result = Integer.parseInt(chaine);
		
		return result;
	}
	
	/**
	 * Over write the function {@code run()}
	 */
	public void run() {
		// TODO Auto-generated method stub
		 while (TakeActive){
			if (gameTable.playerdecided instanceof HumanPlayer&&gameTable.playerdecided!=null) {
				HumanPlayer human = (HumanPlayer) gameTable.playerdecided;
				
				System.out.println("You can choose an offer from ");
				for (Player player : gameTable.playerlist) {
					System.out.println(player);
				}
				
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int tnp = this.readInt();
				System.out.println(gameTable.playerlist.get(tnp).toString());
				
				if (tnp < gameTable.playerlist.size()&&(gameTable.playerlist.get(tnp)!=human||(gameTable.fullofferPlayers().size()==1))) {   //make the situation where one can take his own card clear
					System.out.println(
							"Please take card : you can choose by the code of player and the card(0 for facedown, 1 for faceup):");
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					human.takeOffer(gameTable.playerlist.get(tnp), this.readInt());
					TakeActive=false;
					hasdone=true;
				}

				if (human.hastakencard) {
					gameTable.playerdecided = gameTable.playerlist.get(tnp);
				}
			}
			
		}
		
		
		
	}
	
	/**
	 * To stop the thread from running.
	 */
	public void stop() {
		TakeActive=false;
	}
	
	/**
	 * Over write the function {@code update(Object o)}.
	 */
	public void update(Object o) {
		
	}

}
