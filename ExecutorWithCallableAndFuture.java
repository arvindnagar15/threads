package com.arvind.nagar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * this class is used to run multiple threads using executor framework and 
 * @author an057q
 *
 */
public class ExecutorWithCallableAndFuture {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		callables.add(new ProcessorExecutor());
		List<Future<String>> futures = null;
		try {
			futures = executorService.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
		
		for(Future<String> future : futures){
		    try {
				System.out.println("future.get = " + future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

class ProcessorExecutor implements Callable{
	
	public String processedFile(){
		System.out.println("processedFile: THread Name : "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "File Processed successfulle : "+Thread.currentThread().getName();
	}

	@Override
	public String call() throws Exception {
		return processedFile();
	}
	
	
}