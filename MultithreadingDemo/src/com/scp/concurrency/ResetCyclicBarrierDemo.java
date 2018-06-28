package com.scp.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResetCyclicBarrierDemo {
	static CyclicBarrier cycBar=null;
	
	public static void main(String[] args) throws InterruptedException  {
		cycBar=new CyclicBarrier(10, new Thread(){
			public void run(){
				
				System.out.println("All parties are at barrier....");
			}
			
		});
		One t1=new One(cycBar, "T1");
		One t2=new One(cycBar, "T2");
		One t3=new One(cycBar, "T3");
		
		t1.start();
		t2.start();
		t3.start();
				
		/*One t4=new One(cycBar, "T4");
		One t5=new One(cycBar, "T5");
		One t6=new One(cycBar, "T6");
	
		t4.start();
		t5.start();
		t6.start();
				
		Thread.sleep(4000);
		cycBar.reset();*/
		Two t11=new Two(cycBar, "T111");
		Two t12=new Two(cycBar, "T222");
		Two t13=new Two(cycBar, "T333");		
		t11.start();
		t12.start();
		t13.start();
		
		System.out.println(Thread.currentThread().getName()+" thread is completed...");
	}
}
class One extends Thread{
	CyclicBarrier cycBar=null;

	public One(CyclicBarrier cycBar,String name) {
		super(name);
		this.cycBar = cycBar;
	}
	public void run(){
		System.out.println(Thread.currentThread().getName()+" : waiting for barrier"+getClass());
		try {

			
			cycBar.await();
			//cycBar.reset();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" : crossed the barrier"+getClass());
	}
}
class Two extends Thread{
	CyclicBarrier cycBar=null;
	String name;
	public Two(CyclicBarrier cycBar, String name) {
		super(name);
		this.cycBar = cycBar;
			}
	public void run(){
		//System.out.println(Thread.currentThread().getName()+" : waiting for barrier"+getClass());
		
		try {
			Thread.sleep(2000);
			cycBar.reset();
			cycBar.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName()+" : crossedddddd the barrier"+getClass());
 
	}
}