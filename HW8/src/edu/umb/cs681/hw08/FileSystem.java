package edu.umb.cs681.hw08;

import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem implements Runnable{
	private LinkedList<Directory> rootD = new LinkedList<Directory>();
	protected static ReentrantLock lock = new ReentrantLock();
	
	private static FileSystem fileSystem = null;
	

    public static FileSystem getFileSystem(){
    	lock.lock();
    	try {
    		if(fileSystem==null)
                fileSystem = new FileSystem();
            return fileSystem;
    	} finally {
    		lock.unlock();
    	}
 
    }


	public LinkedList<Directory> getRootDirs() {
		return this.rootD;
		
	}

	public void addRootDir(Directory root) {
		
		lock.lock();
    	try {
    		rootD.add(root);
    	} finally {
    		lock.unlock();
    	}

	}
	

    @Override
    public void run() {}
	
	public static void main(String[] args) {
		fileSystem = FileSystem.getFileSystem();
		LocalDateTime Date = LocalDateTime.now();
	    Directory root = new Directory(null, "root", 0, LocalDateTime.now());
	    Directory home = new Directory(root, "Home", 13, Date);
	    Directory applications = new Directory(root, "Applications", 10, Date);
	    Directory code = new Directory(home, "Code", 13, Date);
	    Thread t1 = new Thread(root);
		Thread t2 = new Thread(applications);
		Thread t3 = new Thread(home);
		Thread t4 = new Thread(code);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
        
	    File a = new File(applications, "a", 1, Date);
	    File b = new File(applications, "b", 2, Date);
	    File c = new File(applications, "c", 3, Date);
	    File d = new File(applications, "d", 4, Date);
	    File e = new File(code, "e", 5, Date);
	    File f = new File(code, "f", 6, Date);
	    Link x = new Link(code, "x", 1, Date, c);
	    Link y = new Link(code, "y", 1, Date, d);
	    Thread t5 = new Thread(a);
		Thread t6 = new Thread(b);
		Thread t7 = new Thread(c);
		Thread t8 = new Thread(d);
		Thread t9 = new Thread(e);
		Thread t10 = new Thread(f);
		Thread t11 = new Thread(x);
		Thread t12 = new Thread(y);
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
	    
	    root.appendChild(home);
        root.appendChild(applications);
        home.appendChild(code);
        applications.appendChild(a);
        applications.appendChild(b);
        home.appendChild(c);
        home.appendChild(d);
        code.appendChild(e);
        code.appendChild(f);
        
        System.out.println("size of root: " + root.getTotalSize());
        System.out.println("size of a: " + a.getSize());
		System.out.println("size of b: " + b.getSize());
		System.out.println("size of x target: " + x.getTargetSize());
		System.out.println("size of y target: " + y.getTargetSize());
	}

}