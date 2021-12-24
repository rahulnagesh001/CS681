package edu.umb.cs681.hw17;

import java.util.HashMap;
import java.util.Map;

public class StockQuoteObservable extends Observable {

	Map<String, Float> map = new HashMap<String, Float>();

	void changeQuote(String t, float q) {
		map.put(t, q);
		setChanged();
		notifyObservers(new StockEvent(t, q));
	}

	public static void main(String[] args) {
		System.out.println("StockQuoteObservable class has been Run");
	}
}
