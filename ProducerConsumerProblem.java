/**
 * 
 */
package com.arvind.nagar;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author an057q
 *	
 *The problem describes two processes, the producer and the consumer, who share a common, fixed-size bucket used as a queue. 
 *The producer's job is to generate data, put it into the bucket, and start again. At the same time, the consumer is consuming the data (i.e., removing it from the bucket), 
 *one piece at a time. The problem is to make sure that the producer won't try to add data into the bucket if it's full and that the consumer won't try to remove data from 
 *an empty bucket.
 *The solution for the producer is to either go to sleep or discard data if the bucket is full. The next time the consumer removes an item from the bucket, it notifies the producer, 
 *who starts to fill the bucket again. In the same way, the consumer can go to sleep if it finds the bucket to be empty. The next time the producer puts data into the bucket, 
 *it wakes up the sleeping consumer.

 */
public class ProducerConsumerProblem {

	public static void main(String[] args) {
		BlockingQueue<Integer> bucket = new ArrayBlockingQueue<Integer>(10);
		Producer producer = new Producer(bucket);
		Consumer consumer = new Consumer(bucket);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}
	
	
	
	
}
class Producer implements Runnable{
	BlockingQueue<Integer> bucket;
	public Producer(BlockingQueue<Integer> bucket){
		this.bucket = bucket;
	}
	Random random = new Random();
	@Override
	public void run() {
		while(true){
		try {
			int value = random.nextInt(100);
			bucket.put(value);
			System.out.println("Inserting : "+value+" : Size : "+bucket.size() );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
class Consumer implements Runnable{
	
	BlockingQueue<Integer> bucket;
	public Consumer(BlockingQueue<Integer> bucket){
		this.bucket = bucket;
	}
	Random random = new Random();
	@Override
	public void run() {
		while(true){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(random.nextInt(10) == 0){
			int value = 0;
			try {
				value = bucket.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Value is : "+value+ " : size : "+bucket.size());
		}
		}
		
	}
	
}
