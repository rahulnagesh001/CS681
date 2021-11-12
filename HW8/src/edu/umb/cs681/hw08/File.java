package edu.umb.cs681.hw08;

import java.time.LocalDateTime;

public class File extends FSElement implements Runnable {
	
	public File(Directory parent, String name, int size, LocalDateTime createdTime) {
		super(parent,name, size, createdTime);
		parent.appendChild(this);
	}

	public boolean isDirectory() {

    	lock.lock();
    	try {
    		return false;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public boolean isFile() {
	

    	lock.lock();
    	try {
    		return true;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public boolean isLink() {
		
		lock.lock();
    	try {
    		return false;
    	} finally {
    		lock.unlock();
    	}
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Running Thread: " + Thread.currentThread().getId());
		} catch(Exception e){
			System.out.println("Exception caught");
		}
	}

}