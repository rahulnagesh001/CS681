package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class EntranceHandler implements Runnable {
	private AdmissionMonitor monitor;
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public EntranceHandler(AdmissionMonitor monitor) {
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
                System.out.println("stop entrance monitoring");
                break;
            }
            monitor.enter();
        }
    }
}
