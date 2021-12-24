package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
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

    @Override
    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {
                if(done) {
                    System.out.println("The Prime Factorizer Stopped running.");
                    break;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2) {
                        divisor++;
                    }
                    else {
                        divisor += 2;
                    }

                }
            }finally {
                lock.unlock();
            }
        }
    }
    public void run() {
        generatePrimeFactors();
         }

    public static void main(String[] args) {
    	RunnableCancellablePrimeFactorizer fact = new RunnableCancellablePrimeFactorizer(125, 2,
				(long) Math.sqrt(125));
		Thread thread = new Thread(fact);
		thread.start();
		fact.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prime Factors of 125: " + fact.getPrimeFactors() + "\n");
	}
}