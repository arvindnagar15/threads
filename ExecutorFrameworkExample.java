/**
 * 
 */
package com.arvind.nagar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author an057q
 * This is the simply example of how executor framework works
 */
public class ExecutorFrameworkExample {
	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i<3; i++){
			executors.submit(new ExecutorFrameworkExampleRunnable(i));
		}
		executors.shutdown();
		
		System.out.println("All tasks submitted");
		try {
			executors.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks completed");
	}

}
class ExecutorFrameworkExampleRunnable implements Runnable{

	private int id;
	public ExecutorFrameworkExampleRunnable(int id){
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Starting thread : "+id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed thread : "+id);
	}
	
}
