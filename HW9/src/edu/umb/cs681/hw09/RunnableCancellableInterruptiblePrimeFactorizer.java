package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        }finally {
            lock.unlock();
        }
    }
    
    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {

                if(done) {
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    break;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            }finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }
    
    public static void main(String[] args) {
    	
    	// Factorize 125
        RunnableCancellablePrimeFactorizer fact1 = new RunnableCancellablePrimeFactorizer(125, 2, (long)Math.sqrt(125));
        Thread thread1 = new Thread(fact1);
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Print factors
        System.out.println("Prime factors of 125: " + fact1.getPrimeFactors() + "\n");
        
       //Factorize 2466
        RunnableCancellablePrimeFactorizer fact2 = new RunnableCancellablePrimeFactorizer(2466, 2, (long)Math.sqrt(2466));
        Thread thread2 = new Thread(fact2);
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        	System.out.println("Interrupted sleep");
        	System.out.println(e.toString() + ".");
        }
        
        fact2.setDone();
        
        thread2.interrupt();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Print factors
        System.out.println("Prime factors of 2466: " + fact2.getPrimeFactors() + "\n");
    }
}