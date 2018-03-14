/**
 * 
 */
package com.arvind.nagar;

/**
 * @author an057q
 *
 */
public class ReentrantLock {

	/**
	 * @param args
	 * This is used to check how to use reentrant locks
	 * similar to synchronize block but easier to use.
	 */
	
	public static void main(String[] args) {
	int i = 0;
	MyIncrementClass incrementClass = new MyIncrementClass(i);
	Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			incrementClass.firstThreadIncrement();
			
		}
	});
	Thread t2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			incrementClass.SecondThreadIncrement();
			
		}
	});
	Thread t3 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			incrementClass.firstThreadIncrement();
			
		}
	});
	Thread t4 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			incrementClass.SecondThreadIncrement();
			
		}
	});
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	}

}
class MyIncrementClass{
	
	private int counter = 0;
	public MyIncrementClass(int count){
		this.counter = count;
	}
	
	private java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
	public void increment(){
		for(int i=0; i<1000; i++){
			counter++;
		}
		System.out.println("Count is : "+counter);
	}
	
	public void firstThreadIncrement(){
		lock.lock();
		increment();
		lock.unlock();
	}
	public void SecondThreadIncrement(){
		lock.lock();
		increment();
		lock.unlock();
	}
}
