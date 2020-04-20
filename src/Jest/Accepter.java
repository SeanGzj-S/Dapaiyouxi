package Jest;
/**
 * The interface {@code Accepter} is part of the design pattern Visitor.
 * Inside the interface, a function {@code accept(Visitor visitor)} is declared.
 */

public interface Accepter {
	 /**
	  * To accept the interface {@code Visitor} to visit.
	  * @param visitor the {@code Visitor} which is accepted.
	  */
     public void accept(Visitor visitor);
}
