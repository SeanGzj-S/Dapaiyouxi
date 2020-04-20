package Jest;

import java.util.LinkedList;

/**
 * The interface {@code Visitor} is part of the design pattern Visitor.
 * It declares a function {@code calculatescores(LinkedList<Player> playerlist)} which
 * can calculate the scores of each player in {@code playerlist}.
 */
public interface Visitor {
	/**
	 * To calculate the score of each player.
	 * @param playerlist is the linked list of the players.
	 */
	public void calculatescores(LinkedList<Player> playerlist);
}
