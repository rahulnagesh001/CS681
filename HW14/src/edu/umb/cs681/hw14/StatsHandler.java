package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class StatsHandler implements Runnable {
	private AdmissionMonitor monitor;
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public StatsHandler(AdmissionMonitor monitor) {
    	this.monitor = monitor;
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void run() {
    	while (true) {
            if(done) {
                break;
            }
            monitor.countCurrentVisitors();
        }
    }
}
