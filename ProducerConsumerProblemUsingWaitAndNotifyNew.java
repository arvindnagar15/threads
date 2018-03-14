package com.arvind.nagar;

import java.util.Scanner;

/**
 * this tells about producer consumer problem using wait and notify.
 * @author an057q
 * Steps : 
1.	Producer add an item into bucket.
2.	After adding item into bucket, notify consumer that they can consume the bucket.
3.	Producer check if bucket size is already full, then wait till there is space available in bucket.
4.	Once consumer notified by producer, it remove items from bucket.
5.	Once consumer remove item, it notify producer that bucket is available to fill more items.
6.	Once bucket size is zero, consumer should wait.

 *
 */
public class ProducerConsumerProblemUsingWaitAndNotifyNew {
	public static void main(String[] args) {
		try{
		HelloProducer producer = new HelloProducer();

		HelloConsumer consumer = new HelloConsumer();
		
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
class HelloProducer implements Runnable{
	@Override
	public void run() {
		synchronized (this) {
			try {
				System.out.println("Thread waiting..."+Thread.currentThread().getName());
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
class HelloConsumer implements Runnable{
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		synchronized (this) {
			try {
				System.out.println("Thread consumer..."+Thread.currentThread().getName());
				//Thread.sleep(1000);
				scanner.nextLine();
				System.out.println("Thread consumer sleep..."+Thread.currentThread().getName());
				notify();
				System.out.println("Thread notified..."+Thread.currentThread().getName());
			} catch (Exception  e1) {
				e1.printStackTrace();
			}

		}


	}

}