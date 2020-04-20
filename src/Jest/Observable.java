package Jest;

import java.util.LinkedList;

import Vue.Observer;

public class Observable {

	public LinkedList<Observer> windows;
	private boolean haschanged;
	
	public Observable() {
		windows = new LinkedList<Observer>();
		haschanged=false;		
	}
	
	public void setchange() {
		this.haschanged=true;
	}
	
	public void clearchange() {
		this.haschanged=false;
	}
	
	public void addObserver(Observer o) {
		windows.add(o);
	}
	
	public void deleteObsever(Observer o) {
		windows.remove(o);
	}
	
	public Observer getObserver(int i) {
		return windows.get(i);
	}
	
	public void notifyAll(Object o){
		if (haschanged) {
			for (Observer observer : windows) {
				observer.update(o);
			}
			clearchange();
		}	
	}
	
	
	
}
