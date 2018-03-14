/**
 * 
 */
package com.arvind.nagar;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author an057q
 * There are three ways we can ensure that only one thread should run at a time
 * 1. Use thread join method
 * 2. Synchronize the entire run method. Using this we can ensure that only one thread will run at the same time, but it won't ensure the order 
 * 	  i.e. if we want to ensure that first thread should run first, follow by second and third, then we should use Thread.join() methood.
 * 3. Using Reentrant lock 
 *
 */
public class OnlyOneThreadRunATOneTime{

	public static void main(String[] args) {
		OnlyOneThreadRunATOneTimeRunnable obj = new OnlyOneThreadRunATOneTimeRunnable();
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		Thread t3 = new Thread(obj);
		try{
		t1.start();
		//t1.join();
		t2.start();
		//t2.join();
		t3.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class OnlyOneThreadRunATOneTimeRunnable implements Runnable{
	ReentrantLock lock = new ReentrantLock();
	
	@Override	
	public void run() {
		lock.lock();
		System.out.println(" inside run method  - Thread Name : "+Thread.currentThread().getName());
		getIncement();
		lock.unlock();
		
	}
	/*public synchronized void run() {
		System.out.println(" inside run method  - Thread Name : "+Thread.currentThread().getName());
		getIncement();
	}*/
	public void getIncement(){
		System.out.println(" inside getIncrement method  - Thread Name : "+Thread.currentThread().getName());
		for(int i= 0 ; i<50; i++){
			System.out.println(" i :"+i + " : Thread Name : "+Thread.currentThread().getName());
		}
	}
}