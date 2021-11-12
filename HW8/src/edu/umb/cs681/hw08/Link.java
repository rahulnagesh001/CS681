package edu.umb.cs681.hw08;

import java.time.LocalDateTime;

public class Link extends FSElement implements Runnable{ 
	private FSElement target;
	public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
		super(parent, name, size, creationTime);
		this.target = target;
		parent.appendChild(this);
	}
	

    public int getTargetSize(){
    	lock.lock();
    	try {
    		return target.getSize();
    	} finally {
    		lock.unlock();
    	}
    }

    
	public FSElement getTarget() {
		
		lock.lock();
		try {
			return target;
		} finally {
			lock.unlock();
		}	
	}

	public boolean isDirectory() {
		lock.lock();
    	try {
    		return false;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public boolean isLink() {
		
		lock.lock();
    	try {
    		return true;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public boolean isFile() {
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