package com.arvind.nagar;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableWithoutExecutor {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		CallableWithoutExecutorProcessor callableWithoutExecutorProcessor = new CallableWithoutExecutorProcessor(map);
		FutureTask<Map<String, String>> futureTask = new FutureTask<>(callableWithoutExecutorProcessor);
		Thread t1 = new Thread(futureTask);
		t1.setName("One");
		Thread t2 = new Thread(futureTask);
		t2.setName("Two");
		Thread t3 = new Thread(futureTask);
		Thread t4 = new Thread(futureTask);
		t1.start();
		t2.start();
		//t3.start();
		//t4.start();
		
		try {
			Map<String, String> resultMap = futureTask.get();
			resultMap.forEach((k,v)->{
				System.out.println("Key : "+k+" : Value : "+v);
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
class CallableWithoutExecutorProcessor implements Callable{
	Map<String, String> map = null;
	int i = 0;
	public CallableWithoutExecutorProcessor(Map<String, String> map2){
		this.map = map2;
	}
	public String sayhello(){
		return "Hello Thread : "+Thread.currentThread().getName();
	}

	@Override
	public Map<String, String> call() {
		System.out.println("Name : "+i+Thread.currentThread().getName());
		map.put(i+"thread", Thread.currentThread().getName());
		return map;
	}
	
}
