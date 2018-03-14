/**
 * 
 */
package com.arvind.nagar;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author an057q
 *
 */
public class ExecutorWithFuture {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future future = executorService.submit(new Runnable() {
			
			@Override
			public void run() throws RuntimeException{
				for(int i= 0 ; i<3; i++){
					//try {
						//Thread.sleep(1000);
						//try {
							if(i == 0){
								throw new RuntimeException();
							}
							
						/*} catch (RuntimeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					/*} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					//System.out.println("Inside run method... "+Thread.currentThread().getName());
				}
				
			}
		});
		
		try {
			System.out.println("Return type : "+future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
