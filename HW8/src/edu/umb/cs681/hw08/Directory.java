package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement implements Runnable{
	
	private LinkedList<FSElement> children = new LinkedList<FSElement>();
	private LinkedList<File> fileList = new LinkedList<File>();
	private LinkedList<Directory> directoryList = new LinkedList<Directory>();
	private LinkedList<Link> linkList = new LinkedList<Link>();
	
	public Directory(Directory parent, String name, int size, LocalDateTime createdTime) {
		super(parent, name, size, createdTime);
		if (parent != null)
			parent.appendChild(this);
	}
	
	public LinkedList<FSElement> getChildren() {
		
		lock.lock();
    	try {
    		return this.children;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public void appendChild(FSElement child) {
		
		lock.lock();
    	try {
    		this.children.add(child);
            child.setParent(this);
    	} finally {
    		lock.unlock();
    	}

	}
	
	public int countChildren() {
		lock.lock();
		
		try {
			return getChildren().size();
		} finally {
			lock.unlock();
		}
		
		
	}
		
	public LinkedList<Directory> getSubDirectories() {
		
		lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof Directory)
    				directoryList.add((Directory) e);
    		}
    		return directoryList;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public LinkedList<File> getFiles() {		
		
		LinkedList<File> files = new LinkedList<File>();
    	lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof File) {
    				files.add((File) e);
    			}
    		}
    		return files;
    	} finally {
    		lock.unlock();
    	}

	}

	public int getTotalSize() {
		int totalSize = 0;
		lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof Directory)
    				totalSize += ((Directory) e).getTotalSize();
    			else
    				totalSize += e.getSize();
    		}
    		return totalSize;
    	} finally {
    		lock.unlock();
    	}

		
	
	}

	public boolean isDirectory() {
		lock.lock();
    	try {
    		return true;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public  boolean isFile() {
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
    		return false;
    	} finally {
    		lock.unlock();
    	}
	}
	
    public LinkedList<Link> getLinks() {
		
		
		lock.lock();
    	try {
    		for (FSElement element : getChildren()) {
    			if (element.isLink())
    				linkList.add((Link) element);
    		}
    		return linkList;
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
