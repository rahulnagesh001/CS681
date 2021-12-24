
package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	private LinkedList<Observer> observers;
	private AtomicBoolean changed = new AtomicBoolean(false); 
	private ReentrantLock lock = new ReentrantLock();

	
	public Observable() {
		observers = new LinkedList<Observer>();
	}
	
	public void addObserver(Observer o) {
		if(o == null) {
			throw new NullPointerException();
		}
		lock.lock();  
	    observers.add(o);
	    lock.unlock();
	}
	
	public void deleteObserver(Observer o) {
		lock.lock();
		if(observers.remove(o)) {
			System.out.println("Observer removed");
		}else {
			System.out.println("This observer does not exist");
		}
		lock.unlock();
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
		    lock.lock();
		    tempObservers = new LinkedList<Observer>(observers);
		    lock.unlock();
		    if (hasChanged()) {
		    	for (Observer obs: tempObservers) {
		    		obs.update(this, arg);
		        }
		        clearChanged();
		        System.out.println("Notify complete.");
		    }
		}
}