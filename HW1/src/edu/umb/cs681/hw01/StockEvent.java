package edu.umb.cs681.hw01;

public class StockEvent {
	public String ticker;
	public float quote;

	public StockEvent(String t, float q) {
		this.ticker = t;
		this.quote = q;
	}

	public String getTicker() {
		return ticker;
	}

	public float getQuote() {
		return quote;
	}

	public static void main(String[] args) {
		System.out.println("StockEvent class has been Run");
	}
}
