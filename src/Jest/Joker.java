package Jest;
public class Joker extends Card{


	public Joker() {
		super();
		setChar();
	}
	
	public void setChar() {
		this.charateristics = "Best Jest";
	}
	
    public boolean isJoker() {
   	return true; 
    }

	
}
