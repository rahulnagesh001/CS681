package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private Directory parent;
	protected ReentrantLock lock = new ReentrantLock();

	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	
	}
	public void setParent(Directory parent) {
		
		lock.lock();
		try {
			this.parent = parent;
		} finally {
			lock.unlock();
		}
		
	}

	public String getName() {
		
		lock.lock();
		try {
			return name;
		} finally {
			lock.unlock();
		}
		
	}

	public int getSize() {
		lock.lock();
		try {
			return this.size;
		} finally {
			lock.unlock();
		}
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public Directory getParent() {
		lock.lock();
		try {
			return parent;
		} finally {
			lock.unlock();
		}
		
		
	}
		
	protected abstract boolean isDirectory();
	protected abstract boolean isFile();
	protected abstract boolean isLink();

}