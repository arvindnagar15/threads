/**
 * 
 */
package com.arvind.nagar;

/**
 * @author an057q
 * This is used to check how to use wait and notify method in threads
 *
 */
public class WaitAndNotifyExample {
	public static void main(String[] args) throws Exception{
		WaitAndNotifyExampleThread obj = new WaitAndNotifyExampleThread();
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		Thread t3 = new Thread(obj);
		Thread t4 = new Thread(obj);
		//Thread t5 = new Thread(obj);
		//Thread t6 = new Thread(obj);
		//Thread t7 = new Thread(obj);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		//t5.start();
		//t6.start();
		//t7.start();
		
		synchronized (t4) {
			try{
				System.out.println("Before waiting : "+Thread.currentThread().getName());
				t4.wait();
				System.out.println("After waiting : "+Thread.currentThread().getName());
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("Final waiting : "+Thread.currentThread().getName()+" : Total : "+obj.total);
		}
	}

}
class WaitAndNotifyExampleThread implements Runnable{
    int total;
    @Override
    public void run(){
    	System.out.println("Thread Name : "+Thread.currentThread().getName());
        synchronized(this){
            for(int i=0; i<5 ; i++){
                total += i;
            }
            System.out.println("Thread Name Before notify : "+Thread.currentThread().getName() +" : Total  :"+total);
            notify();
            System.out.println("Thread Name after notify : "+Thread.currentThread().getName() +" : Total After  :"+total);
        }
    }
}
