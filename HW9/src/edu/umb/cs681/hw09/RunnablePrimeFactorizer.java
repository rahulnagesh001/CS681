package edu.umb.cs681.hw09;

import java.util.LinkedList;



public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {

	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}

	protected boolean isEven(long n) {
		if (n % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void generatePrimeFactors() {
		long divisor = from;
		while (dividend != 1 && divisor <= to) {
			if (divisor > 2 && isEven(divisor)) {
				divisor++;
				continue;
			}
			if (dividend % divisor == 0) {
				factors.add(divisor);
				dividend /= divisor;
			} else {
				if (divisor == 2) {
					divisor++;
				} else {
					divisor += 2;
				}
			}
		}
	}

	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}

	public static void main(String[] args) {
		
		System.out.println("Factorization of 48");
		RunnablePrimeFactorizer runnable = new RunnablePrimeFactorizer(48, 2, (long) Math.sqrt(48));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of " + runnable.getFrom()
				+ "->" + runnable.getTo());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");

		// 
		System.out.println("Factorization of 99");
		LinkedList<RunnablePrimeFactorizer> runnables = new LinkedList<RunnablePrimeFactorizer>();
		LinkedList<Thread> threads = new LinkedList<Thread>();

		runnables.add(new RunnablePrimeFactorizer(99, 2, (long) Math.sqrt(99) / 2));
		runnables.add(new RunnablePrimeFactorizer(99, 1 + (long) Math.sqrt(99) / 2, (long) Math.sqrt(99)));

		thread = new Thread(runnables.get(0));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of "
				+ runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

		thread = new Thread(runnables.get(1));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of "
				+ runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

		threads.forEach((t) -> t.start());
		threads.forEach((t) -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		LinkedList<Long> factors = new LinkedList<Long>();
		runnables.forEach((factorizer) -> factors.addAll(factorizer.getPrimeFactors()));
		System.out.println("Final result: " + factors + "\n");

		runnables.clear();
		threads.clear();

		
		System.out.println("Factorization of 2466");
		runnables.add(new RunnablePrimeFactorizer(2466, 2, (long) Math.sqrt(2466) / 2));
		runnables.add(new RunnablePrimeFactorizer(2466, 1 + (long) Math.sqrt(2466) / 2, (long) Math.sqrt(2466)));

		thread = new Thread(runnables.get(0));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of "
				+ runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

		thread = new Thread(runnables.get(1));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + " performs factorization in the range of "
				+ runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

		threads.forEach((t) -> t.start());
		threads.forEach((t) -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		LinkedList<Long> factors2 = new LinkedList<Long>();
		runnables.forEach((factorizer) -> factors2.addAll(factorizer.getPrimeFactors()));
		System.out.println("Final result: " + factors2);
	}
}