package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable{
	
	private ConcurrentSingleton() {
		
	};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>();
	
	public void run() {
		AtomicReference<ConcurrentSingleton> singletonInstance = ConcurrentSingleton.getInstance();
		System.out.println("Current Instance: " + singletonInstance);
	}
	
	public static AtomicReference<ConcurrentSingleton> getInstance() {
			if(instance.get() == null) {
				instance.set(new ConcurrentSingleton());
			}
		return instance;
	}


	public static void main(String[] args) {
		Thread thread1 = new Thread(new ConcurrentSingleton());
		Thread thread2 = new Thread(new ConcurrentSingleton());
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}