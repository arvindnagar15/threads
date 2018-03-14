/**
 * 
 */
package com.arvind.nagar;

import java.util.concurrent.CountDownLatch;

/**
 * @author an057q
 * Suppose you want to execute a funtionality after all threads finished their tasks, then CountDownLatch is the good approach.
 */
public class CountDownLatches {

	public static void main(String[] args) {
		CountDownLatch latche = new CountDownLatch(4);
		CountDown countDown = new CountDown(latche);
		
		Thread t1 = new Thread(countDown);
		Thread t2 = new Thread(countDown);
		Thread t3 = new Thread(countDown);
		Thread t4 = new Thread(countDown);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			latche.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished after all threads");
	}
}

class CountDown implements Runnable{

	private CountDownLatch countDownLatch; 
	public CountDown(CountDownLatch countDownLatch){
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<2; i++){
			System.out.println("Start Running Thread : "+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("End Running Thread : "+Thread.currentThread().getName());
		}
		countDownLatch.countDown();
	}
	
}