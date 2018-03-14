/**
 * 
 */
package com.arvind.nagar;

/**
 * @author an057q
 * This class explain how to create thread using extending Thread class
 */
public class MyThreadClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread myThread1  = new MyThread();
		
		MyThread myThread2  = new MyThread();
		myThread1.start();
		myThread2.start();

	}

}
class MyThread extends Thread{
	
	public void run(){
		System.out.println(" inside run method  - Thread Name : "+Thread.currentThread().getName());
		getIncement();
	}
	public void getIncement(){
		System.out.println(" inside getIncrement method  - Thread Name : "+Thread.currentThread().getName());
		for(int i= 0 ; i<50; i++){
			System.out.println(" i :"+i + " : Thread Name : "+Thread.currentThread().getName());
		}
	}
}
