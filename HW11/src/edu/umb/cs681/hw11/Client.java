package edu.umb.cs681.hw11;


public class Client implements Runnable{
public static void main(String[] args) {
	Thread t1 = new Thread(new Client());
	Thread t2 = new Thread(new Client());

	t1.start();
	t2.start();

	try {
		t1.join();
		t2.join();
	} catch (Exception e) {
		System.out.println(e);
	}

}

@Override
public void run() {
	Customer customer = new Customer(new Address("Liberty Island", "New York", "NY", 10004));
	System.out.println("Initial Address: " + customer.getAddress());
	customer.setAddress(customer.getAddress().change("Grand Canyon", "Arizona", "AZ", 86023));
	System.out.println("Changed Address: "+ customer.getAddress());
}

}