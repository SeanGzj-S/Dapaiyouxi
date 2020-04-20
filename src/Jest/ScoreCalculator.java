package Jest;

import java.util.LinkedList;
/**
 * The class {@code ScoreCalculator} is part of the design pattern Visitor and it implements the interface {@code Visitor}
 */
public class ScoreCalculator implements Visitor{
	/**
	 * For all the players in the game(in the player list),calculate their scores according to their Jest.
	 */
	public void calculatescores(LinkedList<Player> playerlist) {
		
		for(Player p :playerlist ){

			
			int scores =0 ;
			int[][] valuesuit = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
			boolean hasjoker = false;
			
			for(Card c : p.getJest()) {
				if (c instanceof Joker) {
					hasjoker = true;
				}
				else {
					SuitCard cs = (SuitCard) c;
					valuesuit[cs.getValue().ordinal()][cs.getSuit().ordinal()]=cs.getValue().ordinal()+1;				
				}
			}
			
			for (int i = 0; i < 4; i++) {
	            
				if (valuesuit[0][i]!=0&&valuesuit[1][i]==0&&valuesuit[2][i]==0&&valuesuit[3][i]==0) {
					valuesuit[0][i]=5;
				}
				
				if (valuesuit[i][2]!=0&&valuesuit[i][3]!=0) {
					valuesuit[i][2]+=1;
					valuesuit[i][3]+=1;
				}
				
				valuesuit[i][1]*=-1;
			}
			
			if (hasjoker) {
				if (valuesuit[0][0]==0&&valuesuit[1][0]==0&&valuesuit[2][0]==0&&valuesuit[3][0]==0) {
					scores+=4;
				}
				else if (valuesuit[0][0]!=0&&valuesuit[1][0]!=0&&valuesuit[2][0]!=0&&valuesuit[3][0]!=0) {
				}
				else {
					for(int it = 0; it<4 ; it++ ) {
						valuesuit[it][0]*=-1;
					}
				}
			}
			else {	
				    for(int it = 0; it<4 ; it++ ) {
					    valuesuit[it][0]=0;
				    }
					
				}
			
			for(int m = 0; m<4 ;m++) {
				for (int n = 0; n < 4; n++) {
					scores+=valuesuit[m][n];
				}
			}
			
			p.setscore(scores);
		}
	}
}
