package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
	private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void enter() {
		lock.lock();
		try {
			while (currentVisitors >= 5) {
				condition.await();
				}
				currentVisitors++;
		} catch (InterruptedException exception) {
			System.out.println(exception.toString());
		} finally {
			lock.unlock();
		}
    }

    public void exit() {
		lock.lock();
		try {
			currentVisitors--;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
        	System.out.println("Current visitors: " + currentVisitors);
            return currentVisitors;
        }
        finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        AdmissionMonitor monitor = new AdmissionMonitor();
        EntranceHandler entranceHandler = new EntranceHandler(monitor);
        ExitHandler exitHandler = new ExitHandler(monitor);
        StatsHandler statsHandler = new StatsHandler(monitor);

        Thread entranceThread = new Thread(entranceHandler);
        entranceThread.start();
        Thread exitThread = new Thread(exitHandler);
        exitThread.start();
        Thread statsThread = new Thread(statsHandler);
        statsThread.start();

        try{
            Thread.sleep(1000);
        } catch (Exception e) {
           e.printStackTrace();
        }

        entranceHandler.setDone();
        exitHandler.setDone();
        statsHandler.setDone();
        entranceThread.interrupt();
        exitThread.interrupt();
        statsThread.interrupt();
        try {
            entranceThread.join();
            exitThread.join();
            statsThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
