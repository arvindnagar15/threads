/**
 * 
 */
package com.arvind.nagar;

import java.util.LinkedList;

/**
 * @author an057q
 *This class is used to check how to solve producer consumer problem.
 *Steps : 
1.	Producer add an item into bucket.
2.	After adding item into bucket, notify consumer that they can consume the bucket.
3.	Producer check if bucket size is already full, then wait till there is space available in bucket.
4.	Once consumer notified by producer, it remove items from bucket.
5.	Once consumer remove item, it notify producer that bucket is available to fill more items.
6.	Once bucket size is zero, consumer should wait.

 */
public class ProducerConsumerUsingWaitNotifyLatest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			final Processor processor  = new Processor();
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					processor.producer(); 
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
			//t1.join();
			//t2.join();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
class Processor {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private Object lock = new Object(); 
	private final int SIZE = 10;
	
	int i = 0;
	
	public void producer(){
		while(true){
			synchronized (lock) {
				System.out.println("Producer : inserting...  size : "+list.size());
				while(list.size() == SIZE){
					try {
						System.out.println("Producer : waiting...  size : "+list.size());
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				list.add(i++);
				lock.notify();
				System.out.println("Producer : size : "+list.size());
			}
			
		}
	}
	
	public void consumer() throws InterruptedException{
		while(true){
			synchronized (lock) {
				try {
					System.out.println("consumer : sleping  size : "+list.size());
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(list.size() == 0 ){
					lock.wait();
				}
				System.out.println("consumer : removing  size : "+list.size());
				int value = 0;
				if(list.size() > 0){
					value  = list.removeFirst();
				}
				lock.notify();
				System.out.println("consumer : removed  value.. : "+value+" : size is : "+list.size());
			}
		}
	}
}
