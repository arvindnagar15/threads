/**
 * 
 */
package com.arvind.nagar;

import java.util.LinkedList;

/**
 * @author an057q
 *  This tells about producer consumer problem using wait and notify.
 * 	Steps : 
1.	Producer add an item into bucket.
2.	After adding item into bucket, notify consumer that they can consume the bucket.
3.	Producer check if bucket size is already full, then wait till there is space available in bucket.
4.	Once consumer notified by producer, it remove items from bucket.
5.	Once consumer remove item, it notify producer that bucket is available to fill more items.
6.	Once bucket size is zero, consumer should wait.

 *
 */
public class ProduceConsumerBasedOnDoc {

	public static void main(String[] args) {
		ProcessorNew processor = new ProcessorNew();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}

class ProcessorNew{
	private LinkedList<Integer> list = new LinkedList<>();
	private final int SIZE = 10;
	private Object lock = new Object();
	 int i = 0;
	 
	public void producer() throws InterruptedException{
		
		while(true){
			synchronized(lock){
				System.out.println("Producer : inside..."+list.size());
				while(list.size() == SIZE){
					System.out.println("Producer : waiting ..."+list.size());
					lock.wait();
				}
				list.add(i++);
				System.out.println("Producer : after add and before notify ..."+list.size());
				lock.notify();
				System.out.println("Producer : after add and after notify ..."+list.size());
				}
			}
		
	}
	public void consumer() throws InterruptedException{
		while(true){
			synchronized (lock) {
				System.out.println("Consumer : inside ..."+list.size());
				while(list.size() == 0){
					System.out.println("Consumer : waiting ..."+list.size());
					lock.wait();
				}
				int value = list.removeFirst();
				System.out.println("Producer : after removed and before notify ..."+list.size() +" : Removed value size : "+value);
				lock.notify();
				Thread.sleep(1000);
			}
		}
		
		
	}
}