/**
 * 
 */
package com.arvind.nagar;

/**
 * @author an057q
 * This class explain how to create thread by implemenating runnable interface
 *
 */
public class MyRunnableInterfaceImplementation {

	public static void main(String[] args) {
		RunnableClass obj = new RunnableClass();
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		Thread t3 = new Thread(obj);
		t1.start();
		t2.start();
		t3.start();
	}
}
class RunnableClass implements Runnable{

	@Override
	public void run() {
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
