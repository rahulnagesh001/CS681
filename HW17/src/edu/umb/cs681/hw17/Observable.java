
package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Observable {
	private  ConcurrentLinkedQueue<Observer> observers;
	private AtomicBoolean changed = new AtomicBoolean(false); 

	
	public Observable() {
		observers = new ConcurrentLinkedQueue<Observer>();
	}
	
	public void addObserver(Observer o) {
		if(o == null) {
			throw new NullPointerException();
		}
	    observers.add(o);
	}
	
	public void deleteObserver(Observer o) {
		if(observers.remove(o)) {
			System.out.println("Observer removed");
		}else {
			System.out.println("This observer does not exist");
		}
	}
	
	public void deleteObservers() {
		observers.clear();
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	protected void setChanged() {
		 changed.getAndSet(true); 
	}
	
	protected void clearChanged() {
		changed.getAndSet(false); 
	}
	
	public boolean hasChanged() {
		 if (changed.get()) 
		    	return true;
		    return false;
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg) {
		 LinkedList<Observer> tempObservers = new LinkedList<>();
		    tempObservers = new LinkedList<Observer>(observers);
		    if (hasChanged()) {
		    	for (Observer obs: tempObservers) {
		    		obs.update(this, arg);
		        }
		        clearChanged();
		    }
		}
}