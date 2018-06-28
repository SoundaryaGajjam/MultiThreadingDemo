package com.scp.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class ExectuorsDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyThrd1 t1=new MyThrd1();
		MyThrd1 t2=new MyThrd1();
		
		MyThrd2 t5=new MyThrd2();
		MyThrd2 t6=new MyThrd2();
		MyThrd2 t7=new MyThrd2();
		
		
	//ScheduledExecutorService exSer=Executors.newScheduledThreadPool(19);
	//ExecutorService exSer=Executors.newCachedThreadPool();
	//ExecutorService exSer=Executors.newFixedThreadPool(10);
	//ExecutorService exSer=Executors.newSingleThreadExecutor();
	ScheduledExecutorService exSer=Executors.newSingleThreadScheduledExecutor();
	exSer.execute(t1);
	/*exSer.execute(t2);
	exSer.submit(t5);
	exSer.execute(t2);
	exSer.submit(t6);
	exSer.execute(t1);
	exSer.execute(t2);
	exSer.submit(t5);
	exSer.execute(t1);
	exSer.execute(t2);
	exSer.submit(t5);*/
	exSer.submit(t7);
	
	/*Future<List<Integer>>li=exSer.submit(t7);
	System.out.println(li);*/
	System.out.println(Thread.currentThread().getName()+" : "+exSer.isTerminated());
	Future<Integer>res=exSer.submit(t7);
	//res.cancel(true);
	System.out.println("Res : "+res.get());
	System.out.println("Before shutdown : "+exSer.isShutdown());
	exSer.shutdown();
	//List<Runnable>listRun=exSer.shutdownNow();
	
	exSer.execute(t1);
	//System.out.println("await : "+exSer.awaitTermination(120, TimeUnit.SECONDS));
	//System.out.println("Before terminated : "+exSer.isTerminated());
	System.out.println(Thread.currentThread().getName()+" : Thread is completed....");
	
	
	
	//System.out.println(" :  "+listRun);
	System.out.println("After shutdown : "+exSer.isShutdown());
	/*int i=1;
	while(!exSer.isTerminated()){
		System.out.println("task is yet to complete..."+i);
		i++;
	}*/
		//System.out.println("cancled : "+res.isCancelled());
	//System.out.println("After terminated : "+exSer.isTerminated());
	}
}
class MyThrd1 implements Runnable{
	@Override
	public void run() {
		
		for(int i=0;i<=5;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" : "+i);
			
		}
		
	}
}
class MyThrd2 implements Callable<Integer>{

	/*@Override
	public List<Integer> call() throws Exception {
		List<Integer>list =new ArrayList<>();
		for(int i=100;i<=110;i++){
			//TimeUnit.SECONDS.sleep(1000);
			list.add(i);
			System.out.println(Thread.currentThread().getName()+" : "+list);
		}
		
		return list;
	}
	*/
	//@Override
	public Integer call() throws Exception {
		int sum=0;
		for(int i=100;i<=105;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sum+=i;
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
		
		return (Integer)sum;
	}
	
	
}
