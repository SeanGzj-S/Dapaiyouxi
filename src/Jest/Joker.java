package Jest;
/**
 * {@code Joker} is a kind of card so it extends the class {@code Card}.
 */
public class Joker extends Card{
	
	/**
	 * Constructor of the class {@code Joker}.
	 * The characteristics of the Joker is "Best Jest".
	 */
	public Joker() {
		super();
		this.charateristics = "Best Jest";
	}
	
	/**
	 * To check if the card is a Joker.
	 * The result is true.
	 */
    public boolean isJoker() {
   	return true; 
    }
    
    

	
}
